package com.nuri.social.web.rest;

import com.nuri.social.NuriSocialApp;
import com.nuri.social.domain.AvatarCategory;
import com.nuri.social.repository.AvatarCategoryRepository;
import com.nuri.social.service.AvatarCategoryService;
import com.nuri.social.service.dto.AvatarCategoryDTO;
import com.nuri.social.service.mapper.AvatarCategoryMapper;

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

import com.nuri.social.domain.enumeration.CategoryStatus;
/**
 * Integration tests for the {@link AvatarCategoryResource} REST controller.
 */
@SpringBootTest(classes = NuriSocialApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AvatarCategoryResourceIT {

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final CategoryStatus DEFAULT_STATUS = CategoryStatus.ACTIVATED;
    private static final CategoryStatus UPDATED_STATUS = CategoryStatus.VALID;

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private AvatarCategoryRepository avatarCategoryRepository;

    @Autowired
    private AvatarCategoryMapper avatarCategoryMapper;

    @Autowired
    private AvatarCategoryService avatarCategoryService;

    @Autowired
    private MockMvc restAvatarCategoryMockMvc;

    private AvatarCategory avatarCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AvatarCategory createEntity() {
        AvatarCategory avatarCategory = new AvatarCategory()
            .path(DEFAULT_PATH)
            .name(DEFAULT_NAME)
            .icon(DEFAULT_ICON)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .created(DEFAULT_CREATED)
            .modified(DEFAULT_MODIFIED);
        return avatarCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AvatarCategory createUpdatedEntity() {
        AvatarCategory avatarCategory = new AvatarCategory()
            .path(UPDATED_PATH)
            .name(UPDATED_NAME)
            .icon(UPDATED_ICON)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        return avatarCategory;
    }

    @BeforeEach
    public void initTest() {
        avatarCategoryRepository.deleteAll();
        avatarCategory = createEntity();
    }

    @Test
    public void createAvatarCategory() throws Exception {
        int databaseSizeBeforeCreate = avatarCategoryRepository.findAll().size();
        // Create the AvatarCategory
        AvatarCategoryDTO avatarCategoryDTO = avatarCategoryMapper.toDto(avatarCategory);
        restAvatarCategoryMockMvc.perform(post("/api/avatar-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the AvatarCategory in the database
        List<AvatarCategory> avatarCategoryList = avatarCategoryRepository.findAll();
        assertThat(avatarCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        AvatarCategory testAvatarCategory = avatarCategoryList.get(avatarCategoryList.size() - 1);
        assertThat(testAvatarCategory.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testAvatarCategory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAvatarCategory.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testAvatarCategory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAvatarCategory.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAvatarCategory.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testAvatarCategory.getModified()).isEqualTo(DEFAULT_MODIFIED);
    }

    @Test
    public void createAvatarCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = avatarCategoryRepository.findAll().size();

        // Create the AvatarCategory with an existing ID
        avatarCategory.setId("existing_id");
        AvatarCategoryDTO avatarCategoryDTO = avatarCategoryMapper.toDto(avatarCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAvatarCategoryMockMvc.perform(post("/api/avatar-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AvatarCategory in the database
        List<AvatarCategory> avatarCategoryList = avatarCategoryRepository.findAll();
        assertThat(avatarCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkPathIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarCategoryRepository.findAll().size();
        // set the field null
        avatarCategory.setPath(null);

        // Create the AvatarCategory, which fails.
        AvatarCategoryDTO avatarCategoryDTO = avatarCategoryMapper.toDto(avatarCategory);


        restAvatarCategoryMockMvc.perform(post("/api/avatar-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<AvatarCategory> avatarCategoryList = avatarCategoryRepository.findAll();
        assertThat(avatarCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarCategoryRepository.findAll().size();
        // set the field null
        avatarCategory.setName(null);

        // Create the AvatarCategory, which fails.
        AvatarCategoryDTO avatarCategoryDTO = avatarCategoryMapper.toDto(avatarCategory);


        restAvatarCategoryMockMvc.perform(post("/api/avatar-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<AvatarCategory> avatarCategoryList = avatarCategoryRepository.findAll();
        assertThat(avatarCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarCategoryRepository.findAll().size();
        // set the field null
        avatarCategory.setStatus(null);

        // Create the AvatarCategory, which fails.
        AvatarCategoryDTO avatarCategoryDTO = avatarCategoryMapper.toDto(avatarCategory);


        restAvatarCategoryMockMvc.perform(post("/api/avatar-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<AvatarCategory> avatarCategoryList = avatarCategoryRepository.findAll();
        assertThat(avatarCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarCategoryRepository.findAll().size();
        // set the field null
        avatarCategory.setCreated(null);

        // Create the AvatarCategory, which fails.
        AvatarCategoryDTO avatarCategoryDTO = avatarCategoryMapper.toDto(avatarCategory);


        restAvatarCategoryMockMvc.perform(post("/api/avatar-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<AvatarCategory> avatarCategoryList = avatarCategoryRepository.findAll();
        assertThat(avatarCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkModifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = avatarCategoryRepository.findAll().size();
        // set the field null
        avatarCategory.setModified(null);

        // Create the AvatarCategory, which fails.
        AvatarCategoryDTO avatarCategoryDTO = avatarCategoryMapper.toDto(avatarCategory);


        restAvatarCategoryMockMvc.perform(post("/api/avatar-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<AvatarCategory> avatarCategoryList = avatarCategoryRepository.findAll();
        assertThat(avatarCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllAvatarCategories() throws Exception {
        // Initialize the database
        avatarCategoryRepository.save(avatarCategory);

        // Get all the avatarCategoryList
        restAvatarCategoryMockMvc.perform(get("/api/avatar-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(avatarCategory.getId())))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modified").value(hasItem(DEFAULT_MODIFIED.toString())));
    }
    
    @Test
    public void getAvatarCategory() throws Exception {
        // Initialize the database
        avatarCategoryRepository.save(avatarCategory);

        // Get the avatarCategory
        restAvatarCategoryMockMvc.perform(get("/api/avatar-categories/{id}", avatarCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(avatarCategory.getId()))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modified").value(DEFAULT_MODIFIED.toString()));
    }
    @Test
    public void getNonExistingAvatarCategory() throws Exception {
        // Get the avatarCategory
        restAvatarCategoryMockMvc.perform(get("/api/avatar-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAvatarCategory() throws Exception {
        // Initialize the database
        avatarCategoryRepository.save(avatarCategory);

        int databaseSizeBeforeUpdate = avatarCategoryRepository.findAll().size();

        // Update the avatarCategory
        AvatarCategory updatedAvatarCategory = avatarCategoryRepository.findById(avatarCategory.getId()).get();
        updatedAvatarCategory
            .path(UPDATED_PATH)
            .name(UPDATED_NAME)
            .icon(UPDATED_ICON)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        AvatarCategoryDTO avatarCategoryDTO = avatarCategoryMapper.toDto(updatedAvatarCategory);

        restAvatarCategoryMockMvc.perform(put("/api/avatar-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the AvatarCategory in the database
        List<AvatarCategory> avatarCategoryList = avatarCategoryRepository.findAll();
        assertThat(avatarCategoryList).hasSize(databaseSizeBeforeUpdate);
        AvatarCategory testAvatarCategory = avatarCategoryList.get(avatarCategoryList.size() - 1);
        assertThat(testAvatarCategory.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testAvatarCategory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAvatarCategory.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testAvatarCategory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAvatarCategory.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAvatarCategory.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testAvatarCategory.getModified()).isEqualTo(UPDATED_MODIFIED);
    }

    @Test
    public void updateNonExistingAvatarCategory() throws Exception {
        int databaseSizeBeforeUpdate = avatarCategoryRepository.findAll().size();

        // Create the AvatarCategory
        AvatarCategoryDTO avatarCategoryDTO = avatarCategoryMapper.toDto(avatarCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAvatarCategoryMockMvc.perform(put("/api/avatar-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avatarCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AvatarCategory in the database
        List<AvatarCategory> avatarCategoryList = avatarCategoryRepository.findAll();
        assertThat(avatarCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAvatarCategory() throws Exception {
        // Initialize the database
        avatarCategoryRepository.save(avatarCategory);

        int databaseSizeBeforeDelete = avatarCategoryRepository.findAll().size();

        // Delete the avatarCategory
        restAvatarCategoryMockMvc.perform(delete("/api/avatar-categories/{id}", avatarCategory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AvatarCategory> avatarCategoryList = avatarCategoryRepository.findAll();
        assertThat(avatarCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
