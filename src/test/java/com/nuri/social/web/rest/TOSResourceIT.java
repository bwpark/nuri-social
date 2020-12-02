package com.nuri.social.web.rest;

import com.nuri.social.NuriSocialApp;
import com.nuri.social.domain.TOS;
import com.nuri.social.repository.TOSRepository;
import com.nuri.social.service.TOSService;
import com.nuri.social.service.dto.TOSDTO;
import com.nuri.social.service.mapper.TOSMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TOSResource} REST controller.
 */
@SpringBootTest(classes = NuriSocialApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TOSResourceIT {

    private static final String DEFAULT_POLICY = "AAAAAAAAAA";
    private static final String UPDATED_POLICY = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    @Autowired
    private TOSRepository tOSRepository;

    @Autowired
    private TOSMapper tOSMapper;

    @Autowired
    private TOSService tOSService;

    @Autowired
    private MockMvc restTOSMockMvc;

    private TOS tOS;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TOS createEntity() {
        TOS tOS = new TOS()
            .policy(DEFAULT_POLICY)
            .text(DEFAULT_TEXT);
        return tOS;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TOS createUpdatedEntity() {
        TOS tOS = new TOS()
            .policy(UPDATED_POLICY)
            .text(UPDATED_TEXT);
        return tOS;
    }

    @BeforeEach
    public void initTest() {
        tOSRepository.deleteAll();
        tOS = createEntity();
    }

    @Test
    public void createTOS() throws Exception {
        int databaseSizeBeforeCreate = tOSRepository.findAll().size();
        // Create the TOS
        TOSDTO tOSDTO = tOSMapper.toDto(tOS);
        restTOSMockMvc.perform(post("/api/tos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tOSDTO)))
            .andExpect(status().isCreated());

        // Validate the TOS in the database
        List<TOS> tOSList = tOSRepository.findAll();
        assertThat(tOSList).hasSize(databaseSizeBeforeCreate + 1);
        TOS testTOS = tOSList.get(tOSList.size() - 1);
        assertThat(testTOS.getPolicy()).isEqualTo(DEFAULT_POLICY);
        assertThat(testTOS.getText()).isEqualTo(DEFAULT_TEXT);
    }

    @Test
    public void createTOSWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tOSRepository.findAll().size();

        // Create the TOS with an existing ID
        tOS.setId("existing_id");
        TOSDTO tOSDTO = tOSMapper.toDto(tOS);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTOSMockMvc.perform(post("/api/tos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tOSDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TOS in the database
        List<TOS> tOSList = tOSRepository.findAll();
        assertThat(tOSList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllTOS() throws Exception {
        // Initialize the database
        tOSRepository.save(tOS);

        // Get all the tOSList
        restTOSMockMvc.perform(get("/api/tos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tOS.getId())))
            .andExpect(jsonPath("$.[*].policy").value(hasItem(DEFAULT_POLICY)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT.toString())));
    }
    
    @Test
    public void getTOS() throws Exception {
        // Initialize the database
        tOSRepository.save(tOS);

        // Get the tOS
        restTOSMockMvc.perform(get("/api/tos/{id}", tOS.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tOS.getId()))
            .andExpect(jsonPath("$.policy").value(DEFAULT_POLICY))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT.toString()));
    }
    @Test
    public void getNonExistingTOS() throws Exception {
        // Get the tOS
        restTOSMockMvc.perform(get("/api/tos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTOS() throws Exception {
        // Initialize the database
        tOSRepository.save(tOS);

        int databaseSizeBeforeUpdate = tOSRepository.findAll().size();

        // Update the tOS
        TOS updatedTOS = tOSRepository.findById(tOS.getId()).get();
        updatedTOS
            .policy(UPDATED_POLICY)
            .text(UPDATED_TEXT);
        TOSDTO tOSDTO = tOSMapper.toDto(updatedTOS);

        restTOSMockMvc.perform(put("/api/tos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tOSDTO)))
            .andExpect(status().isOk());

        // Validate the TOS in the database
        List<TOS> tOSList = tOSRepository.findAll();
        assertThat(tOSList).hasSize(databaseSizeBeforeUpdate);
        TOS testTOS = tOSList.get(tOSList.size() - 1);
        assertThat(testTOS.getPolicy()).isEqualTo(UPDATED_POLICY);
        assertThat(testTOS.getText()).isEqualTo(UPDATED_TEXT);
    }

    @Test
    public void updateNonExistingTOS() throws Exception {
        int databaseSizeBeforeUpdate = tOSRepository.findAll().size();

        // Create the TOS
        TOSDTO tOSDTO = tOSMapper.toDto(tOS);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTOSMockMvc.perform(put("/api/tos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tOSDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TOS in the database
        List<TOS> tOSList = tOSRepository.findAll();
        assertThat(tOSList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteTOS() throws Exception {
        // Initialize the database
        tOSRepository.save(tOS);

        int databaseSizeBeforeDelete = tOSRepository.findAll().size();

        // Delete the tOS
        restTOSMockMvc.perform(delete("/api/tos/{id}", tOS.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TOS> tOSList = tOSRepository.findAll();
        assertThat(tOSList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
