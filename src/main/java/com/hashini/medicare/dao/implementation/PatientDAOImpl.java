package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.PatientDAO;
import com.hashini.medicare.dto.PatientDTO;
import com.hashini.medicare.mapper.PatientMapper;
import com.hashini.medicare.model.Patient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PatientDAOImpl implements PatientDAO {

    private final JdbcTemplate jdbcTemplate;

    public PatientDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PatientDTO> selectPatients() {
        String sql = "SELECT * " +
                "FROM patient " +
                "LEFT JOIN prescription p on patient.patient_id = p.patient_id " +
                "ORDER BY patient.patient_id DESC";
        return new ArrayList<>((Objects.requireNonNull(jdbcTemplate.query(sql, new PatientMapper()))).values());
    }

    @Override
    public Optional<PatientDTO> selectPatientById(long id) {
        String sql = "SELECT * " +
                "FROM patient " +
                "LEFT JOIN prescription p on patient.patient_id = p.patient_id " +
                "WHERE patient.patient_id = ?";
        return new ArrayList<>(Objects.requireNonNull(jdbcTemplate.query(sql, new PatientMapper(), id)).values())
                .stream()
                .findFirst();
    }

    @Override
    public List<PatientDTO> selectPatientsByName(String patientName) {
        String sql = "SELECT * " +
                "FROM patient " +
                "LEFT JOIN prescription p on patient.patient_id = p.patient_id " +
                "WHERE LOWER(patient.patient_name) LIKE '%" + patientName.toLowerCase() + "%' ORDER BY patient.patient_id DESC";
        return new ArrayList<>((Objects.requireNonNull(jdbcTemplate.query(sql, new PatientMapper()))).values());
    }

    @Override
    public long addPatient(Patient patient) {
        String sql = "INSERT INTO patient(patient_name,age,gender) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"patient_id"});
            ps.setString(1, patient.getName());
            ps.setInt(2, patient.getAge());
            ps.setString(3, patient.getGender());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public long updatePatient(Patient patient, long id) {
        String sql = "UPDATE patient SET patient_name = ?, age = ?, gender = ? WHERE patient_id = ?";
        return jdbcTemplate.update(sql, patient.getName(), patient.getAge(), patient.getGender(), id);
    }

    @Override
    public int deletePatient(long id) {
        String sql = "DELETE FROM patient WHERE patient_id = ?";
        return jdbcTemplate.update(sql, id);
    }
}