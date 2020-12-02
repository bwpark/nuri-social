package com.nuri.social.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.social.web.rest.TestUtil;

public class IssueResourceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IssueResource.class);
        IssueResource issueResource1 = new IssueResource();
        issueResource1.setId("id1");
        IssueResource issueResource2 = new IssueResource();
        issueResource2.setId(issueResource1.getId());
        assertThat(issueResource1).isEqualTo(issueResource2);
        issueResource2.setId("id2");
        assertThat(issueResource1).isNotEqualTo(issueResource2);
        issueResource1.setId(null);
        assertThat(issueResource1).isNotEqualTo(issueResource2);
    }
}
