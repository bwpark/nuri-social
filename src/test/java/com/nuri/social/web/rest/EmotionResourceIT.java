package com.nuri.social.web.rest;

import com.nuri.social.NuriSocialApp;
import com.nuri.social.domain.Emotion;
import com.nuri.social.repository.EmotionRepository;
import com.nuri.social.service.EmotionService;
import com.nuri.social.service.dto.EmotionDTO;
import com.nuri.social.service.mapper.EmotionMapper;

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

import com.nuri.social.domain.enumeration.EmotionStatus;
/**
 * Integration tests for the {@link EmotionResource} REST controller.
 */
@SpringBootTest(classes = NuriSocialApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EmotionResourceIT {

    private static final EmotionStatus DEFAULT_STATUS = EmotionStatus.RESPECT;
    private static final EmotionStatus UPDATED_STATUS = EmotionStatus.DISS;

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private EmotionRepository emotionRepository;

    @Autowired
    private EmotionMapper emotionMapper;

    @Autowired
    private EmotionService emotionService;

    @Autowired
    private MockMvc restEmotionMockMvc;

    private Emotion emotion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Emotion createEntity() {
        Emotion emotion = new Emotion()
            .status(DEFAULT_STATUS)
            .created(DEFAULT_CREATED)
            .modified(DEFAULT_MODIFIED);
        return emotion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Emotion createUpdatedEntity() {
        Emotion emotion = new Emotion()
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        return emotion;
    }

    @BeforeEach
    public void initTest() {
        emotionRepository.deleteAll();
        emotion = createEntity();
    }

    @Test
    public void createEmotion() throws Exception {
        int databaseSizeBeforeCreate = emotionRepository.findAll().size();
        // Create the Emotion
        EmotionDTO emotionDTO = emotionMapper.toDto(emotion);
        restEmotionMockMvc.perform(post("/api/emotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emotionDTO)))
            .andExpect(status().isCreated());

        // Validate the Emotion in the database
        List<Emotion> emotionList = emotionRepository.findAll();
        assertThat(emotionList).hasSize(databaseSizeBeforeCreate + 1);
        Emotion testEmotion = emotionList.get(emotionList.size() - 1);
        assertThat(testEmotion.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEmotion.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testEmotion.getModified()).isEqualTo(DEFAULT_MODIFIED);
    }

    @Test
    public void createEmotionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emotionRepository.findAll().size();

        // Create the Emotion with an existing ID
        emotion.setId("existing_id");
        EmotionDTO emotionDTO = emotionMapper.toDto(emotion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmotionMockMvc.perform(post("/api/emotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emotionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Emotion in the database
        List<Emotion> emotionList = emotionRepository.findAll();
        assertThat(emotionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = emotionRepository.findAll().size();
        // set the field null
        emotion.setStatus(null);

        // Create the Emotion, which fails.
        EmotionDTO emotionDTO = emotionMapper.toDto(emotion);


        restEmotionMockMvc.perform(post("/api/emotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emotionDTO)))
            .andExpect(status().isBadRequest());

        List<Emotion> emotionList = emotionRepository.findAll();
        assertThat(emotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = emotionRepository.findAll().size();
        // set the field null
        emotion.setCreated(null);

        // Create the Emotion, which fails.
        EmotionDTO emotionDTO = emotionMapper.toDto(emotion);


        restEmotionMockMvc.perform(post("/api/emotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emotionDTO)))
            .andExpect(status().isBadRequest());

        List<Emotion> emotionList = emotionRepository.findAll();
        assertThat(emotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkModifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = emotionRepository.findAll().size();
        // set the field null
        emotion.setModified(null);

        // Create the Emotion, which fails.
        EmotionDTO emotionDTO = emotionMapper.toDto(emotion);


        restEmotionMockMvc.perform(post("/api/emotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emotionDTO)))
            .andExpect(status().isBadRequest());

        List<Emotion> emotionList = emotionRepository.findAll();
        assertThat(emotionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllEmotions() throws Exception {
        // Initialize the database
        emotionRepository.save(emotion);

        // Get all the emotionList
        restEmotionMockMvc.perform(get("/api/emotions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emotion.getId())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modified").value(hasItem(DEFAULT_MODIFIED.toString())));
    }
    
    @Test
    public void getEmotion() throws Exception {
        // Initialize the database
        emotionRepository.save(emotion);

        // Get the emotion
        restEmotionMockMvc.perform(get("/api/emotions/{id}", emotion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(emotion.getId()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modified").value(DEFAULT_MODIFIED.toString()));
    }
    @Test
    public void getNonExistingEmotion() throws Exception {
        // Get the emotion
        restEmotionMockMvc.perform(get("/api/emotions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEmotion() throws Exception {
        // Initialize the database
        emotionRepository.save(emotion);

        int databaseSizeBeforeUpdate = emotionRepository.findAll().size();

        // Update the emotion
        Emotion updatedEmotion = emotionRepository.findById(emotion.getId()).get();
        updatedEmotion
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        EmotionDTO emotionDTO = emotionMapper.toDto(updatedEmotion);

        restEmotionMockMvc.perform(put("/api/emotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emotionDTO)))
            .andExpect(status().isOk());

        // Validate the Emotion in the database
        List<Emotion> emotionList = emotionRepository.findAll();
        assertThat(emotionList).hasSize(databaseSizeBeforeUpdate);
        Emotion testEmotion = emotionList.get(emotionList.size() - 1);
        assertThat(testEmotion.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEmotion.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testEmotion.getModified()).isEqualTo(UPDATED_MODIFIED);
    }

    @Test
    public void updateNonExistingEmotion() throws Exception {
        int databaseSizeBeforeUpdate = emotionRepository.findAll().size();

        // Create the Emotion
        EmotionDTO emotionDTO = emotionMapper.toDto(emotion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmotionMockMvc.perform(put("/api/emotions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emotionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Emotion in the database
        List<Emotion> emotionList = emotionRepository.findAll();
        assertThat(emotionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteEmotion() throws Exception {
        // Initialize the database
        emotionRepository.save(emotion);

        int databaseSizeBeforeDelete = emotionRepository.findAll().size();

        // Delete the emotion
        restEmotionMockMvc.perform(delete("/api/emotions/{id}", emotion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Emotion> emotionList = emotionRepository.findAll();
        assertThat(emotionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
