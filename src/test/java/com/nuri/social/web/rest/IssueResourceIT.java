package com.nuri.social.web.rest;

import com.nuri.social.NuriSocialApp;
import com.nuri.social.domain.Issue;
import com.nuri.social.repository.IssueRepository;
import com.nuri.social.service.IssueService;
import com.nuri.social.service.dto.IssueDTO;
import com.nuri.social.service.mapper.IssueMapper;

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

import com.nuri.social.domain.enumeration.IssueStatus;
/**
 * Integration tests for the {@link IssueResource} REST controller.
 */
@SpringBootTest(classes = NuriSocialApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class IssueResourceIT {

    private static final String DEFAULT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

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

    private static final Integer DEFAULT_VIEWS = 1;
    private static final Integer UPDATED_VIEWS = 2;

    private static final Integer DEFAULT_COMMENTS = 1;
    private static final Integer UPDATED_COMMENTS = 2;

    private static final IssueStatus DEFAULT_STATUS = IssueStatus.ORIGINATE;
    private static final IssueStatus UPDATED_STATUS = IssueStatus.ORIGINATE;

    private static final Instant DEFAULT_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private IssueMapper issueMapper;

    @Autowired
    private IssueService issueService;

    @Autowired
    private MockMvc restIssueMockMvc;

    private Issue issue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Issue createEntity() {
        Issue issue = new Issue()
            .categoryName(DEFAULT_CATEGORY_NAME)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .text(DEFAULT_TEXT)
            .coin(DEFAULT_COIN)
            .point(DEFAULT_POINT)
            .respect(DEFAULT_RESPECT)
            .diss(DEFAULT_DISS)
            .author(DEFAULT_AUTHOR)
            .views(DEFAULT_VIEWS)
            .comments(DEFAULT_COMMENTS)
            .status(DEFAULT_STATUS)
            .created(DEFAULT_CREATED)
            .modified(DEFAULT_MODIFIED);
        return issue;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Issue createUpdatedEntity() {
        Issue issue = new Issue()
            .categoryName(UPDATED_CATEGORY_NAME)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .text(UPDATED_TEXT)
            .coin(UPDATED_COIN)
            .point(UPDATED_POINT)
            .respect(UPDATED_RESPECT)
            .diss(UPDATED_DISS)
            .author(UPDATED_AUTHOR)
            .views(UPDATED_VIEWS)
            .comments(UPDATED_COMMENTS)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        return issue;
    }

    @BeforeEach
    public void initTest() {
        issueRepository.deleteAll();
        issue = createEntity();
    }

    @Test
    public void createIssue() throws Exception {
        int databaseSizeBeforeCreate = issueRepository.findAll().size();
        // Create the Issue
        IssueDTO issueDTO = issueMapper.toDto(issue);
        restIssueMockMvc.perform(post("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isCreated());

        // Validate the Issue in the database
        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeCreate + 1);
        Issue testIssue = issueList.get(issueList.size() - 1);
        assertThat(testIssue.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
        assertThat(testIssue.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testIssue.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testIssue.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testIssue.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testIssue.getPoint()).isEqualTo(DEFAULT_POINT);
        assertThat(testIssue.getRespect()).isEqualTo(DEFAULT_RESPECT);
        assertThat(testIssue.getDiss()).isEqualTo(DEFAULT_DISS);
        assertThat(testIssue.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
        assertThat(testIssue.getViews()).isEqualTo(DEFAULT_VIEWS);
        assertThat(testIssue.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testIssue.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testIssue.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testIssue.getModified()).isEqualTo(DEFAULT_MODIFIED);
    }

    @Test
    public void createIssueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = issueRepository.findAll().size();

        // Create the Issue with an existing ID
        issue.setId("existing_id");
        IssueDTO issueDTO = issueMapper.toDto(issue);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIssueMockMvc.perform(post("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Issue in the database
        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueRepository.findAll().size();
        // set the field null
        issue.setName(null);

        // Create the Issue, which fails.
        IssueDTO issueDTO = issueMapper.toDto(issue);


        restIssueMockMvc.perform(post("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isBadRequest());

        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCoinIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueRepository.findAll().size();
        // set the field null
        issue.setCoin(null);

        // Create the Issue, which fails.
        IssueDTO issueDTO = issueMapper.toDto(issue);


        restIssueMockMvc.perform(post("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isBadRequest());

        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPointIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueRepository.findAll().size();
        // set the field null
        issue.setPoint(null);

        // Create the Issue, which fails.
        IssueDTO issueDTO = issueMapper.toDto(issue);


        restIssueMockMvc.perform(post("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isBadRequest());

        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkRespectIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueRepository.findAll().size();
        // set the field null
        issue.setRespect(null);

        // Create the Issue, which fails.
        IssueDTO issueDTO = issueMapper.toDto(issue);


        restIssueMockMvc.perform(post("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isBadRequest());

        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDissIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueRepository.findAll().size();
        // set the field null
        issue.setDiss(null);

        // Create the Issue, which fails.
        IssueDTO issueDTO = issueMapper.toDto(issue);


        restIssueMockMvc.perform(post("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isBadRequest());

        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkAuthorIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueRepository.findAll().size();
        // set the field null
        issue.setAuthor(null);

        // Create the Issue, which fails.
        IssueDTO issueDTO = issueMapper.toDto(issue);


        restIssueMockMvc.perform(post("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isBadRequest());

        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkViewsIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueRepository.findAll().size();
        // set the field null
        issue.setViews(null);

        // Create the Issue, which fails.
        IssueDTO issueDTO = issueMapper.toDto(issue);


        restIssueMockMvc.perform(post("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isBadRequest());

        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCommentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueRepository.findAll().size();
        // set the field null
        issue.setComments(null);

        // Create the Issue, which fails.
        IssueDTO issueDTO = issueMapper.toDto(issue);


        restIssueMockMvc.perform(post("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isBadRequest());

        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueRepository.findAll().size();
        // set the field null
        issue.setStatus(null);

        // Create the Issue, which fails.
        IssueDTO issueDTO = issueMapper.toDto(issue);


        restIssueMockMvc.perform(post("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isBadRequest());

        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCreatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueRepository.findAll().size();
        // set the field null
        issue.setCreated(null);

        // Create the Issue, which fails.
        IssueDTO issueDTO = issueMapper.toDto(issue);


        restIssueMockMvc.perform(post("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isBadRequest());

        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkModifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = issueRepository.findAll().size();
        // set the field null
        issue.setModified(null);

        // Create the Issue, which fails.
        IssueDTO issueDTO = issueMapper.toDto(issue);


        restIssueMockMvc.perform(post("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isBadRequest());

        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllIssues() throws Exception {
        // Initialize the database
        issueRepository.save(issue);

        // Get all the issueList
        restIssueMockMvc.perform(get("/api/issues?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(issue.getId())))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT.toString())))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT)))
            .andExpect(jsonPath("$.[*].respect").value(hasItem(DEFAULT_RESPECT)))
            .andExpect(jsonPath("$.[*].diss").value(hasItem(DEFAULT_DISS)))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR)))
            .andExpect(jsonPath("$.[*].views").value(hasItem(DEFAULT_VIEWS)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].modified").value(hasItem(DEFAULT_MODIFIED.toString())));
    }
    
    @Test
    public void getIssue() throws Exception {
        // Initialize the database
        issueRepository.save(issue);

        // Get the issue
        restIssueMockMvc.perform(get("/api/issues/{id}", issue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(issue.getId()))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT.toString()))
            .andExpect(jsonPath("$.coin").value(DEFAULT_COIN))
            .andExpect(jsonPath("$.point").value(DEFAULT_POINT))
            .andExpect(jsonPath("$.respect").value(DEFAULT_RESPECT))
            .andExpect(jsonPath("$.diss").value(DEFAULT_DISS))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR))
            .andExpect(jsonPath("$.views").value(DEFAULT_VIEWS))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.modified").value(DEFAULT_MODIFIED.toString()));
    }
    @Test
    public void getNonExistingIssue() throws Exception {
        // Get the issue
        restIssueMockMvc.perform(get("/api/issues/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateIssue() throws Exception {
        // Initialize the database
        issueRepository.save(issue);

        int databaseSizeBeforeUpdate = issueRepository.findAll().size();

        // Update the issue
        Issue updatedIssue = issueRepository.findById(issue.getId()).get();
        updatedIssue
            .categoryName(UPDATED_CATEGORY_NAME)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .text(UPDATED_TEXT)
            .coin(UPDATED_COIN)
            .point(UPDATED_POINT)
            .respect(UPDATED_RESPECT)
            .diss(UPDATED_DISS)
            .author(UPDATED_AUTHOR)
            .views(UPDATED_VIEWS)
            .comments(UPDATED_COMMENTS)
            .status(UPDATED_STATUS)
            .created(UPDATED_CREATED)
            .modified(UPDATED_MODIFIED);
        IssueDTO issueDTO = issueMapper.toDto(updatedIssue);

        restIssueMockMvc.perform(put("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isOk());

        // Validate the Issue in the database
        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeUpdate);
        Issue testIssue = issueList.get(issueList.size() - 1);
        assertThat(testIssue.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testIssue.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testIssue.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testIssue.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testIssue.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testIssue.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testIssue.getRespect()).isEqualTo(UPDATED_RESPECT);
        assertThat(testIssue.getDiss()).isEqualTo(UPDATED_DISS);
        assertThat(testIssue.getAuthor()).isEqualTo(UPDATED_AUTHOR);
        assertThat(testIssue.getViews()).isEqualTo(UPDATED_VIEWS);
        assertThat(testIssue.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testIssue.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testIssue.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testIssue.getModified()).isEqualTo(UPDATED_MODIFIED);
    }

    @Test
    public void updateNonExistingIssue() throws Exception {
        int databaseSizeBeforeUpdate = issueRepository.findAll().size();

        // Create the Issue
        IssueDTO issueDTO = issueMapper.toDto(issue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIssueMockMvc.perform(put("/api/issues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(issueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Issue in the database
        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteIssue() throws Exception {
        // Initialize the database
        issueRepository.save(issue);

        int databaseSizeBeforeDelete = issueRepository.findAll().size();

        // Delete the issue
        restIssueMockMvc.perform(delete("/api/issues/{id}", issue.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Issue> issueList = issueRepository.findAll();
        assertThat(issueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
