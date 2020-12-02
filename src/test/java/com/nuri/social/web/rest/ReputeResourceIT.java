package com.nuri.social.web.rest;

import com.nuri.social.NuriSocialApp;
import com.nuri.social.domain.Repute;
import com.nuri.social.repository.ReputeRepository;
import com.nuri.social.service.ReputeService;
import com.nuri.social.service.dto.ReputeDTO;
import com.nuri.social.service.mapper.ReputeMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nuri.social.domain.enumeration.ReputeStatus;
/**
 * Integration tests for the {@link ReputeResource} REST controller.
 */
@SpringBootTest(classes = NuriSocialApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ReputeResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_GRADE = 1;
    private static final Integer UPDATED_GRADE = 2;

    private static final Integer DEFAULT_CREDIT = 1;
    private static final Integer UPDATED_CREDIT = 2;

    private static final ReputeStatus DEFAULT_STATUS = ReputeStatus.ORIGINATE;
    private static final ReputeStatus UPDATED_STATUS = ReputeStatus.ORIGINATE;

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ReputeRepository reputeRepository;

    @Autowired
    private ReputeMapper reputeMapper;

    @Autowired
    private ReputeService reputeService;

    @Autowired
    private MockMvc restReputeMockMvc;

    private Repute repute;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Repute createEntity() {
        Repute repute = new Repute()
            .description(DEFAULT_DESCRIPTION)
            .grade(DEFAULT_GRADE)
            .credit(DEFAULT_CREDIT)
            .status(DEFAULT_STATUS)
            .created(DEFAULT_CREATED)
            .modified(DEFAULT_MODIFIED);
        return repute;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Repute createUpdatedEntity() {
        Repute repute = new Repute()
            .description(UPDATED_DESCRIPTION)
            .grade(UPDATED_GRADE)
            .credit(UPDATED_CREDIT)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        return repute;
    }

    @BeforeEach
    public void initTest() {
        reputeRepository.deleteAll();
        repute = createEntity();
    }

    @Test
    public void createRepute() throws Exception {
        int databaseSizeBeforeCreate = reputeRepository.findAll().size();
        // Create the Repute
        ReputeDTO reputeDTO = reputeMapper.toDto(repute);
        restReputeMockMvc.perform(post("/api/reputes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reputeDTO)))
            .andExpect(status().isCreated());

        // Validate the Repute in the database
        List<Repute> reputeList = reputeRepository.findAll();
        assertThat(reputeList).hasSize(databaseSizeBeforeCreate + 1);
        Repute testRepute = reputeList.get(reputeList.size() - 1);
        assertThat(testRepute.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRepute.getGrade()).isEqualTo(DEFAULT_GRADE);
        assertThat(testRepute.getCredit()).isEqualTo(DEFAULT_CREDIT);
        assertThat(testRepute.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRepute.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testRepute.getModified()).isEqualTo(DEFAULT_MODIFIED);
    }

    @Test
    public void createReputeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reputeRepository.findAll().size();

        // Create the Repute with an existing ID
        repute.setId("existing_id");
        ReputeDTO reputeDTO = reputeMapper.toDto(repute);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReputeMockMvc.perform(post("/api/reputes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reputeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Repute in the database
        List<Repute> reputeList = reputeRepository.findAll();
        assertThat(reputeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkGradeIsRequired() throws Exception {
        int databaseSizeBeforeTest = reputeRepository.findAll().size();
        // set the field null
        repute.setGrade(null);

        // Create the Repute, which fails.
        ReputeDTO reputeDTO = reputeMapper.toDto(repute);


        restReputeMockMvc.perform(post("/api/reputes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reputeDTO)))
            .andExpect(status().isBadRequest());

        List<Repute> reputeList = reputeRepository.findAll();
        assertThat(reputeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreditIsRequired() throws Exception {
        int databaseSizeBeforeTest = reputeRepository.findAll().size();
        // set the field null
        repute.setCredit(null);

        // Create the Repute, which fails.
        ReputeDTO reputeDTO = reputeMapper.toDto(repute);


        restReputeMockMvc.perform(post("/api/reputes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reputeDTO)))
            .andExpect(status().isBadRequest());

        List<Repute> reputeList = reputeRepository.findAll();
        assertThat(reputeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = reputeRepository.findAll().size();
        // set the field null
        repute.setStatus(null);

        // Create the Repute, which fails.
        ReputeDTO reputeDTO = reputeMapper.toDto(repute);


        restReputeMockMvc.perform(post("/api/reputes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reputeDTO)))
            .andExpect(status().isBadRequest());

        List<Repute> reputeList = reputeRepository.findAll();
        assertThat(reputeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = reputeRepository.findAll().size();
        // set the field null
        repute.setCreated(null);

        // Create the Repute, which fails.
        ReputeDTO reputeDTO = reputeMapper.toDto(repute);


        restReputeMockMvc.perform(post("/api/reputes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reputeDTO)))
            .andExpect(status().isBadRequest());

        List<Repute> reputeList = reputeRepository.findAll();
        assertThat(reputeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkModifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = reputeRepository.findAll().size();
        // set the field null
        repute.setModified(null);

        // Create the Repute, which fails.
        ReputeDTO reputeDTO = reputeMapper.toDto(repute);


        restReputeMockMvc.perform(post("/api/reputes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reputeDTO)))
            .andExpect(status().isBadRequest());

        List<Repute> reputeList = reputeRepository.findAll();
        assertThat(reputeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllReputes() throws Exception {
        // Initialize the database
        reputeRepository.save(repute);

        // Get all the reputeList
        restReputeMockMvc.perform(get("/api/reputes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(repute.getId())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].credit").value(hasItem(DEFAULT_CREDIT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modified").value(hasItem(DEFAULT_MODIFIED.toString())));
    }
    
    @Test
    public void getRepute() throws Exception {
        // Initialize the database
        reputeRepository.save(repute);

        // Get the repute
        restReputeMockMvc.perform(get("/api/reputes/{id}", repute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(repute.getId()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.grade").value(DEFAULT_GRADE))
            .andExpect(jsonPath("$.credit").value(DEFAULT_CREDIT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modified").value(DEFAULT_MODIFIED.toString()));
    }
    @Test
    public void getNonExistingRepute() throws Exception {
        // Get the repute
        restReputeMockMvc.perform(get("/api/reputes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRepute() throws Exception {
        // Initialize the database
        reputeRepository.save(repute);

        int databaseSizeBeforeUpdate = reputeRepository.findAll().size();

        // Update the repute
        Repute updatedRepute = reputeRepository.findById(repute.getId()).get();
        updatedRepute
            .description(UPDATED_DESCRIPTION)
            .grade(UPDATED_GRADE)
            .credit(UPDATED_CREDIT)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        ReputeDTO reputeDTO = reputeMapper.toDto(updatedRepute);

        restReputeMockMvc.perform(put("/api/reputes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reputeDTO)))
            .andExpect(status().isOk());

        // Validate the Repute in the database
        List<Repute> reputeList = reputeRepository.findAll();
        assertThat(reputeList).hasSize(databaseSizeBeforeUpdate);
        Repute testRepute = reputeList.get(reputeList.size() - 1);
        assertThat(testRepute.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRepute.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testRepute.getCredit()).isEqualTo(UPDATED_CREDIT);
        assertThat(testRepute.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRepute.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testRepute.getModified()).isEqualTo(UPDATED_MODIFIED);
    }

    @Test
    public void updateNonExistingRepute() throws Exception {
        int databaseSizeBeforeUpdate = reputeRepository.findAll().size();

        // Create the Repute
        ReputeDTO reputeDTO = reputeMapper.toDto(repute);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReputeMockMvc.perform(put("/api/reputes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reputeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Repute in the database
        List<Repute> reputeList = reputeRepository.findAll();
        assertThat(reputeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteRepute() throws Exception {
        // Initialize the database
        reputeRepository.save(repute);

        int databaseSizeBeforeDelete = reputeRepository.findAll().size();

        // Delete the repute
        restReputeMockMvc.perform(delete("/api/reputes/{id}", repute.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Repute> reputeList = reputeRepository.findAll();
        assertThat(reputeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
