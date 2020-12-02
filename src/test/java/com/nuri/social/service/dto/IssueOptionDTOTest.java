package com.nuri.social.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.social.web.rest.TestUtil;

public class IssueOptionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IssueOptionDTO.class);
        IssueOptionDTO issueOptionDTO1 = new IssueOptionDTO();
        issueOptionDTO1.setId("id1");
        IssueOptionDTO issueOptionDTO2 = new IssueOptionDTO();
        assertThat(issueOptionDTO1).isNotEqualTo(issueOptionDTO2);
        issueOptionDTO2.setId(issueOptionDTO1.getId());
        assertThat(issueOptionDTO1).isEqualTo(issueOptionDTO2);
        issueOptionDTO2.setId("id2");
        assertThat(issueOptionDTO1).isNotEqualTo(issueOptionDTO2);
        issueOptionDTO1.setId(null);
        assertThat(issueOptionDTO1).isNotEqualTo(issueOptionDTO2);
    }
}
