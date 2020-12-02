package com.nuri.social.web.rest;

import com.nuri.social.NuriSocialApp;
import com.nuri.social.domain.Chemistry;
import com.nuri.social.repository.ChemistryRepository;
import com.nuri.social.service.ChemistryService;
import com.nuri.social.service.dto.ChemistryDTO;
import com.nuri.social.service.mapper.ChemistryMapper;

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

/**
 * Integration tests for the {@link ChemistryResource} REST controller.
 */
@SpringBootTest(classes = NuriSocialApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ChemistryResourceIT {

    private static final String DEFAULT_YOUR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_YOUR_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_TO_YOU = 1;
    private static final Integer UPDATED_TO_YOU = 2;

    private static final Integer DEFAULT_TO_ME = 1;
    private static final Integer UPDATED_TO_ME = 2;

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ChemistryRepository chemistryRepository;

    @Autowired
    private ChemistryMapper chemistryMapper;

    @Autowired
    private ChemistryService chemistryService;

    @Autowired
    private MockMvc restChemistryMockMvc;

    private Chemistry chemistry;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Chemistry createEntity() {
        Chemistry chemistry = new Chemistry()
            .yourName(DEFAULT_YOUR_NAME)
            .toYou(DEFAULT_TO_YOU)
            .toMe(DEFAULT_TO_ME)
            .created(DEFAULT_CREATED);
        return chemistry;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Chemistry createUpdatedEntity() {
        Chemistry chemistry = new Chemistry()
            .yourName(UPDATED_YOUR_NAME)
            .toYou(UPDATED_TO_YOU)
            .toMe(UPDATED_TO_ME)
            .created(UPDATED_CREATED);
        return chemistry;
    }

    @BeforeEach
    public void initTest() {
        chemistryRepository.deleteAll();
        chemistry = createEntity();
    }

    @Test
    public void createChemistry() throws Exception {
        int databaseSizeBeforeCreate = chemistryRepository.findAll().size();
        // Create the Chemistry
        ChemistryDTO chemistryDTO = chemistryMapper.toDto(chemistry);
        restChemistryMockMvc.perform(post("/api/chemistries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chemistryDTO)))
            .andExpect(status().isCreated());

        // Validate the Chemistry in the database
        List<Chemistry> chemistryList = chemistryRepository.findAll();
        assertThat(chemistryList).hasSize(databaseSizeBeforeCreate + 1);
        Chemistry testChemistry = chemistryList.get(chemistryList.size() - 1);
        assertThat(testChemistry.getYourName()).isEqualTo(DEFAULT_YOUR_NAME);
        assertThat(testChemistry.getToYou()).isEqualTo(DEFAULT_TO_YOU);
        assertThat(testChemistry.getToMe()).isEqualTo(DEFAULT_TO_ME);
        assertThat(testChemistry.getCreated()).isEqualTo(DEFAULT_CREATED);
    }

    @Test
    public void createChemistryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chemistryRepository.findAll().size();

        // Create the Chemistry with an existing ID
        chemistry.setId("existing_id");
        ChemistryDTO chemistryDTO = chemistryMapper.toDto(chemistry);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChemistryMockMvc.perform(post("/api/chemistries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chemistryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Chemistry in the database
        List<Chemistry> chemistryList = chemistryRepository.findAll();
        assertThat(chemistryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkYourNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = chemistryRepository.findAll().size();
        // set the field null
        chemistry.setYourName(null);

        // Create the Chemistry, which fails.
        ChemistryDTO chemistryDTO = chemistryMapper.toDto(chemistry);


        restChemistryMockMvc.perform(post("/api/chemistries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chemistryDTO)))
            .andExpect(status().isBadRequest());

        List<Chemistry> chemistryList = chemistryRepository.findAll();
        assertThat(chemistryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkToYouIsRequired() throws Exception {
        int databaseSizeBeforeTest = chemistryRepository.findAll().size();
        // set the field null
        chemistry.setToYou(null);

        // Create the Chemistry, which fails.
        ChemistryDTO chemistryDTO = chemistryMapper.toDto(chemistry);


        restChemistryMockMvc.perform(post("/api/chemistries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chemistryDTO)))
            .andExpect(status().isBadRequest());

        List<Chemistry> chemistryList = chemistryRepository.findAll();
        assertThat(chemistryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkToMeIsRequired() throws Exception {
        int databaseSizeBeforeTest = chemistryRepository.findAll().size();
        // set the field null
        chemistry.setToMe(null);

        // Create the Chemistry, which fails.
        ChemistryDTO chemistryDTO = chemistryMapper.toDto(chemistry);


        restChemistryMockMvc.perform(post("/api/chemistries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chemistryDTO)))
            .andExpect(status().isBadRequest());

        List<Chemistry> chemistryList = chemistryRepository.findAll();
        assertThat(chemistryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = chemistryRepository.findAll().size();
        // set the field null
        chemistry.setCreated(null);

        // Create the Chemistry, which fails.
        ChemistryDTO chemistryDTO = chemistryMapper.toDto(chemistry);


        restChemistryMockMvc.perform(post("/api/chemistries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chemistryDTO)))
            .andExpect(status().isBadRequest());

        List<Chemistry> chemistryList = chemistryRepository.findAll();
        assertThat(chemistryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllChemistries() throws Exception {
        // Initialize the database
        chemistryRepository.save(chemistry);

        // Get all the chemistryList
        restChemistryMockMvc.perform(get("/api/chemistries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chemistry.getId())))
            .andExpect(jsonPath("$.[*].yourName").value(hasItem(DEFAULT_YOUR_NAME)))
            .andExpect(jsonPath("$.[*].toYou").value(hasItem(DEFAULT_TO_YOU)))
            .andExpect(jsonPath("$.[*].toMe").value(hasItem(DEFAULT_TO_ME)))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())));
    }
    
    @Test
    public void getChemistry() throws Exception {
        // Initialize the database
        chemistryRepository.save(chemistry);

        // Get the chemistry
        restChemistryMockMvc.perform(get("/api/chemistries/{id}", chemistry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chemistry.getId()))
            .andExpect(jsonPath("$.yourName").value(DEFAULT_YOUR_NAME))
            .andExpect(jsonPath("$.toYou").value(DEFAULT_TO_YOU))
            .andExpect(jsonPath("$.toMe").value(DEFAULT_TO_ME))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()));
    }
    @Test
    public void getNonExistingChemistry() throws Exception {
        // Get the chemistry
        restChemistryMockMvc.perform(get("/api/chemistries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateChemistry() throws Exception {
        // Initialize the database
        chemistryRepository.save(chemistry);

        int databaseSizeBeforeUpdate = chemistryRepository.findAll().size();

        // Update the chemistry
        Chemistry updatedChemistry = chemistryRepository.findById(chemistry.getId()).get();
        updatedChemistry
            .yourName(UPDATED_YOUR_NAME)
            .toYou(UPDATED_TO_YOU)
            .toMe(UPDATED_TO_ME)
            .created(UPDATED_CREATED);
        ChemistryDTO chemistryDTO = chemistryMapper.toDto(updatedChemistry);

        restChemistryMockMvc.perform(put("/api/chemistries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chemistryDTO)))
            .andExpect(status().isOk());

        // Validate the Chemistry in the database
        List<Chemistry> chemistryList = chemistryRepository.findAll();
        assertThat(chemistryList).hasSize(databaseSizeBeforeUpdate);
        Chemistry testChemistry = chemistryList.get(chemistryList.size() - 1);
        assertThat(testChemistry.getYourName()).isEqualTo(UPDATED_YOUR_NAME);
        assertThat(testChemistry.getToYou()).isEqualTo(UPDATED_TO_YOU);
        assertThat(testChemistry.getToMe()).isEqualTo(UPDATED_TO_ME);
        assertThat(testChemistry.getCreated()).isEqualTo(UPDATED_CREATED);
    }

    @Test
    public void updateNonExistingChemistry() throws Exception {
        int databaseSizeBeforeUpdate = chemistryRepository.findAll().size();

        // Create the Chemistry
        ChemistryDTO chemistryDTO = chemistryMapper.toDto(chemistry);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChemistryMockMvc.perform(put("/api/chemistries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chemistryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Chemistry in the database
        List<Chemistry> chemistryList = chemistryRepository.findAll();
        assertThat(chemistryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteChemistry() throws Exception {
        // Initialize the database
        chemistryRepository.save(chemistry);

        int databaseSizeBeforeDelete = chemistryRepository.findAll().size();

        // Delete the chemistry
        restChemistryMockMvc.perform(delete("/api/chemistries/{id}", chemistry.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Chemistry> chemistryList = chemistryRepository.findAll();
        assertThat(chemistryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
