package com.nuri.social.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class IssueResourceMapperTest {

    private IssueResourceMapper issueResourceMapper;

    @BeforeEach
    public void setUp() {
        issueResourceMapper = new IssueResourceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(issueResourceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(issueResourceMapper.fromId(null)).isNull();
    }
}
