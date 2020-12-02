package com.nuri.social.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class IssueOptionMapperTest {

    private IssueOptionMapper issueOptionMapper;

    @BeforeEach
    public void setUp() {
        issueOptionMapper = new IssueOptionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(issueOptionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(issueOptionMapper.fromId(null)).isNull();
    }
}
