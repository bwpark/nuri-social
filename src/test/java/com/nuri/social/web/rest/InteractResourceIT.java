package com.nuri.social.web.rest;

import com.nuri.social.NuriSocialApp;
import com.nuri.social.domain.Interact;
import com.nuri.social.repository.InteractRepository;
import com.nuri.social.service.InteractService;
import com.nuri.social.service.dto.InteractDTO;
import com.nuri.social.service.mapper.InteractMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nuri.social.domain.enumeration.InteractStatus;
/**
 * Integration tests for the {@link InteractResource} REST controller.
 */
@SpringBootTest(classes = NuriSocialApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InteractResourceIT {

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final Integer DEFAULT_COIN = 1;
    private static final Integer UPDATED_COIN = 2;

    private static final Integer DEFAULT_POINT = 1;
    private static final Integer UPDATED_POINT = 2;

    private static final Integer DEFAULT_RESPECT = 1;
    private static final Integer UPDATED_RESPECT = 2;

    private static final Integer DEFAULT_DISS = 1;
    private static final Integer UPDATED_DISS = 2;

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    private static final InteractStatus DEFAULT_STATUS = InteractStatus.ORIGINATE;
    private static final InteractStatus UPDATED_STATUS = InteractStatus.READED;

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private InteractRepository interactRepository;

    @Autowired
    private InteractMapper interactMapper;

    @Autowired
    private InteractService interactService;

    @Autowired
    private MockMvc restInteractMockMvc;

    private Interact interact;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Interact createEntity() {
        Interact interact = new Interact()
            .text(DEFAULT_TEXT)
            .coin(DEFAULT_COIN)
            .point(DEFAULT_POINT)
            .respect(DEFAULT_RESPECT)
            .diss(DEFAULT_DISS)
            .author(DEFAULT_AUTHOR)
            .status(DEFAULT_STATUS)
            .created(DEFAULT_CREATED)
            .modified(DEFAULT_MODIFIED);
        return interact;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Interact createUpdatedEntity() {
        Interact interact = new Interact()
            .text(UPDATED_TEXT)
            .coin(UPDATED_COIN)
            .point(UPDATED_POINT)
            .respect(UPDATED_RESPECT)
            .diss(UPDATED_DISS)
            .author(UPDATED_AUTHOR)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        return interact;
    }

    @BeforeEach
    public void initTest() {
        interactRepository.deleteAll();
        interact = createEntity();
    }

    @Test
    public void createInteract() throws Exception {
        int databaseSizeBeforeCreate = interactRepository.findAll().size();
        // Create the Interact
        InteractDTO interactDTO = interactMapper.toDto(interact);
        restInteractMockMvc.perform(post("/api/interacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(interactDTO)))
            .andExpect(status().isCreated());

        // Validate the Interact in the database
        List<Interact> interactList = interactRepository.findAll();
        assertThat(interactList).hasSize(databaseSizeBeforeCreate + 1);
        Interact testInteract = interactList.get(interactList.size() - 1);
        assertThat(testInteract.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testInteract.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testInteract.getPoint()).isEqualTo(DEFAULT_POINT);
        assertThat(testInteract.getRespect()).isEqualTo(DEFAULT_RESPECT);
        assertThat(testInteract.getDiss()).isEqualTo(DEFAULT_DISS);
        assertThat(testInteract.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
        assertThat(testInteract.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInteract.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testInteract.getModified()).isEqualTo(DEFAULT_MODIFIED);
    }

    @Test
    public void createInteractWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = interactRepository.findAll().size();

        // Create the Interact with an existing ID
        interact.setId("existing_id");
        InteractDTO interactDTO = interactMapper.toDto(interact);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInteractMockMvc.perform(post("/api/interacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(interactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Interact in the database
        List<Interact> interactList = interactRepository.findAll();
        assertThat(interactList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = interactRepository.findAll().size();
        // set the field null
        interact.setCoin(null);

        // Create the Interact, which fails.
        InteractDTO interactDTO = interactMapper.toDto(interact);


        restInteractMockMvc.perform(post("/api/interacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(interactDTO)))
            .andExpect(status().isBadRequest());

        List<Interact> interactList = interactRepository.findAll();
        assertThat(interactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = interactRepository.findAll().size();
        // set the field null
        interact.setPoint(null);

        // Create the Interact, which fails.
        InteractDTO interactDTO = interactMapper.toDto(interact);


        restInteractMockMvc.perform(post("/api/interacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(interactDTO)))
            .andExpect(status().isBadRequest());

        List<Interact> interactList = interactRepository.findAll();
        assertThat(interactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkRespectIsRequired() throws Exception {
        int databaseSizeBeforeTest = interactRepository.findAll().size();
        // set the field null
        interact.setRespect(null);

        // Create the Interact, which fails.
        InteractDTO interactDTO = interactMapper.toDto(interact);


        restInteractMockMvc.perform(post("/api/interacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(interactDTO)))
            .andExpect(status().isBadRequest());

        List<Interact> interactList = interactRepository.findAll();
        assertThat(interactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDissIsRequired() throws Exception {
        int databaseSizeBeforeTest = interactRepository.findAll().size();
        // set the field null
        interact.setDiss(null);

        // Create the Interact, which fails.
        InteractDTO interactDTO = interactMapper.toDto(interact);


        restInteractMockMvc.perform(post("/api/interacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(interactDTO)))
            .andExpect(status().isBadRequest());

        List<Interact> interactList = interactRepository.findAll();
        assertThat(interactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAuthorIsRequired() throws Exception {
        int databaseSizeBeforeTest = interactRepository.findAll().size();
        // set the field null
        interact.setAuthor(null);

        // Create the Interact, which fails.
        InteractDTO interactDTO = interactMapper.toDto(interact);


        restInteractMockMvc.perform(post("/api/interacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(interactDTO)))
            .andExpect(status().isBadRequest());

        List<Interact> interactList = interactRepository.findAll();
        assertThat(interactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = interactRepository.findAll().size();
        // set the field null
        interact.setStatus(null);

        // Create the Interact, which fails.
        InteractDTO interactDTO = interactMapper.toDto(interact);


        restInteractMockMvc.perform(post("/api/interacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(interactDTO)))
            .andExpect(status().isBadRequest());

        List<Interact> interactList = interactRepository.findAll();
        assertThat(interactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = interactRepository.findAll().size();
        // set the field null
        interact.setCreated(null);

        // Create the Interact, which fails.
        InteractDTO interactDTO = interactMapper.toDto(interact);


        restInteractMockMvc.perform(post("/api/interacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(interactDTO)))
            .andExpect(status().isBadRequest());

        List<Interact> interactList = interactRepository.findAll();
        assertThat(interactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkModifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = interactRepository.findAll().size();
        // set the field null
        interact.setModified(null);

        // Create the Interact, which fails.
        InteractDTO interactDTO = interactMapper.toDto(interact);


        restInteractMockMvc.perform(post("/api/interacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(interactDTO)))
            .andExpect(status().isBadRequest());

        List<Interact> interactList = interactRepository.findAll();
        assertThat(interactList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllInteracts() throws Exception {
        // Initialize the database
        interactRepository.save(interact);

        // Get all the interactList
        restInteractMockMvc.perform(get("/api/interacts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(interact.getId())))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT.toString())))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT)))
            .andExpect(jsonPath("$.[*].respect").value(hasItem(DEFAULT_RESPECT)))
            .andExpect(jsonPath("$.[*].diss").value(hasItem(DEFAULT_DISS)))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modified").value(hasItem(DEFAULT_MODIFIED.toString())));
    }
    
    @Test
    public void getInteract() throws Exception {
        // Initialize the database
        interactRepository.save(interact);

        // Get the interact
        restInteractMockMvc.perform(get("/api/interacts/{id}", interact.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(interact.getId()))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT.toString()))
            .andExpect(jsonPath("$.coin").value(DEFAULT_COIN))
            .andExpect(jsonPath("$.point").value(DEFAULT_POINT))
            .andExpect(jsonPath("$.respect").value(DEFAULT_RESPECT))
            .andExpect(jsonPath("$.diss").value(DEFAULT_DISS))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modified").value(DEFAULT_MODIFIED.toString()));
    }
    @Test
    public void getNonExistingInteract() throws Exception {
        // Get the interact
        restInteractMockMvc.perform(get("/api/interacts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateInteract() throws Exception {
        // Initialize the database
        interactRepository.save(interact);

        int databaseSizeBeforeUpdate = interactRepository.findAll().size();

        // Update the interact
        Interact updatedInteract = interactRepository.findById(interact.getId()).get();
        updatedInteract
            .text(UPDATED_TEXT)
            .coin(UPDATED_COIN)
            .point(UPDATED_POINT)
            .respect(UPDATED_RESPECT)
            .diss(UPDATED_DISS)
            .author(UPDATED_AUTHOR)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        InteractDTO interactDTO = interactMapper.toDto(updatedInteract);

        restInteractMockMvc.perform(put("/api/interacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(interactDTO)))
            .andExpect(status().isOk());

        // Validate the Interact in the database
        List<Interact> interactList = interactRepository.findAll();
        assertThat(interactList).hasSize(databaseSizeBeforeUpdate);
        Interact testInteract = interactList.get(interactList.size() - 1);
        assertThat(testInteract.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testInteract.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testInteract.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testInteract.getRespect()).isEqualTo(UPDATED_RESPECT);
        assertThat(testInteract.getDiss()).isEqualTo(UPDATED_DISS);
        assertThat(testInteract.getAuthor()).isEqualTo(UPDATED_AUTHOR);
        assertThat(testInteract.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInteract.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testInteract.getModified()).isEqualTo(UPDATED_MODIFIED);
    }

    @Test
    public void updateNonExistingInteract() throws Exception {
        int databaseSizeBeforeUpdate = interactRepository.findAll().size();

        // Create the Interact
        InteractDTO interactDTO = interactMapper.toDto(interact);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInteractMockMvc.perform(put("/api/interacts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(interactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Interact in the database
        List<Interact> interactList = interactRepository.findAll();
        assertThat(interactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteInteract() throws Exception {
        // Initialize the database
        interactRepository.save(interact);

        int databaseSizeBeforeDelete = interactRepository.findAll().size();

        // Delete the interact
        restInteractMockMvc.perform(delete("/api/interacts/{id}", interact.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Interact> interactList = interactRepository.findAll();
        assertThat(interactList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
