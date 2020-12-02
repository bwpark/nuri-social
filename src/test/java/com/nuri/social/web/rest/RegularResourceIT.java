package com.nuri.social.web.rest;

import com.nuri.social.NuriSocialApp;
import com.nuri.social.domain.Regular;
import com.nuri.social.repository.RegularRepository;
import com.nuri.social.service.RegularService;
import com.nuri.social.service.dto.RegularDTO;
import com.nuri.social.service.mapper.RegularMapper;

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

import com.nuri.social.domain.enumeration.RegularStatus;
/**
 * Integration tests for the {@link RegularResource} REST controller.
 */
@SpringBootTest(classes = NuriSocialApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RegularResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final RegularStatus DEFAULT_STATUS = RegularStatus.ORIGINATE;
    private static final RegularStatus UPDATED_STATUS = RegularStatus.ORIGINATE;

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private RegularRepository regularRepository;

    @Autowired
    private RegularMapper regularMapper;

    @Autowired
    private RegularService regularService;

    @Autowired
    private MockMvc restRegularMockMvc;

    private Regular regular;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Regular createEntity() {
        Regular regular = new Regular()
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS)
            .created(DEFAULT_CREATED)
            .modified(DEFAULT_MODIFIED);
        return regular;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Regular createUpdatedEntity() {
        Regular regular = new Regular()
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        return regular;
    }

    @BeforeEach
    public void initTest() {
        regularRepository.deleteAll();
        regular = createEntity();
    }

    @Test
    public void createRegular() throws Exception {
        int databaseSizeBeforeCreate = regularRepository.findAll().size();
        // Create the Regular
        RegularDTO regularDTO = regularMapper.toDto(regular);
        restRegularMockMvc.perform(post("/api/regulars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(regularDTO)))
            .andExpect(status().isCreated());

        // Validate the Regular in the database
        List<Regular> regularList = regularRepository.findAll();
        assertThat(regularList).hasSize(databaseSizeBeforeCreate + 1);
        Regular testRegular = regularList.get(regularList.size() - 1);
        assertThat(testRegular.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRegular.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRegular.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testRegular.getModified()).isEqualTo(DEFAULT_MODIFIED);
    }

    @Test
    public void createRegularWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = regularRepository.findAll().size();

        // Create the Regular with an existing ID
        regular.setId("existing_id");
        RegularDTO regularDTO = regularMapper.toDto(regular);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegularMockMvc.perform(post("/api/regulars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(regularDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Regular in the database
        List<Regular> regularList = regularRepository.findAll();
        assertThat(regularList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = regularRepository.findAll().size();
        // set the field null
        regular.setName(null);

        // Create the Regular, which fails.
        RegularDTO regularDTO = regularMapper.toDto(regular);


        restRegularMockMvc.perform(post("/api/regulars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(regularDTO)))
            .andExpect(status().isBadRequest());

        List<Regular> regularList = regularRepository.findAll();
        assertThat(regularList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = regularRepository.findAll().size();
        // set the field null
        regular.setStatus(null);

        // Create the Regular, which fails.
        RegularDTO regularDTO = regularMapper.toDto(regular);


        restRegularMockMvc.perform(post("/api/regulars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(regularDTO)))
            .andExpect(status().isBadRequest());

        List<Regular> regularList = regularRepository.findAll();
        assertThat(regularList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = regularRepository.findAll().size();
        // set the field null
        regular.setCreated(null);

        // Create the Regular, which fails.
        RegularDTO regularDTO = regularMapper.toDto(regular);


        restRegularMockMvc.perform(post("/api/regulars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(regularDTO)))
            .andExpect(status().isBadRequest());

        List<Regular> regularList = regularRepository.findAll();
        assertThat(regularList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkModifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = regularRepository.findAll().size();
        // set the field null
        regular.setModified(null);

        // Create the Regular, which fails.
        RegularDTO regularDTO = regularMapper.toDto(regular);


        restRegularMockMvc.perform(post("/api/regulars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(regularDTO)))
            .andExpect(status().isBadRequest());

        List<Regular> regularList = regularRepository.findAll();
        assertThat(regularList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllRegulars() throws Exception {
        // Initialize the database
        regularRepository.save(regular);

        // Get all the regularList
        restRegularMockMvc.perform(get("/api/regulars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(regular.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modified").value(hasItem(DEFAULT_MODIFIED.toString())));
    }
    
    @Test
    public void getRegular() throws Exception {
        // Initialize the database
        regularRepository.save(regular);

        // Get the regular
        restRegularMockMvc.perform(get("/api/regulars/{id}", regular.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(regular.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modified").value(DEFAULT_MODIFIED.toString()));
    }
    @Test
    public void getNonExistingRegular() throws Exception {
        // Get the regular
        restRegularMockMvc.perform(get("/api/regulars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRegular() throws Exception {
        // Initialize the database
        regularRepository.save(regular);

        int databaseSizeBeforeUpdate = regularRepository.findAll().size();

        // Update the regular
        Regular updatedRegular = regularRepository.findById(regular.getId()).get();
        updatedRegular
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        RegularDTO regularDTO = regularMapper.toDto(updatedRegular);

        restRegularMockMvc.perform(put("/api/regulars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(regularDTO)))
            .andExpect(status().isOk());

        // Validate the Regular in the database
        List<Regular> regularList = regularRepository.findAll();
        assertThat(regularList).hasSize(databaseSizeBeforeUpdate);
        Regular testRegular = regularList.get(regularList.size() - 1);
        assertThat(testRegular.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRegular.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRegular.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testRegular.getModified()).isEqualTo(UPDATED_MODIFIED);
    }

    @Test
    public void updateNonExistingRegular() throws Exception {
        int databaseSizeBeforeUpdate = regularRepository.findAll().size();

        // Create the Regular
        RegularDTO regularDTO = regularMapper.toDto(regular);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegularMockMvc.perform(put("/api/regulars")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(regularDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Regular in the database
        List<Regular> regularList = regularRepository.findAll();
        assertThat(regularList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteRegular() throws Exception {
        // Initialize the database
        regularRepository.save(regular);

        int databaseSizeBeforeDelete = regularRepository.findAll().size();

        // Delete the regular
        restRegularMockMvc.perform(delete("/api/regulars/{id}", regular.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Regular> regularList = regularRepository.findAll();
        assertThat(regularList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
