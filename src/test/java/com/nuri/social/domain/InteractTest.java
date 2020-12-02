package com.nuri.social.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.social.web.rest.TestUtil;

public class InteractTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Interact.class);
        Interact interact1 = new Interact();
        interact1.setId("id1");
        Interact interact2 = new Interact();
        interact2.setId(interact1.getId());
        assertThat(interact1).isEqualTo(interact2);
        interact2.setId("id2");
        assertThat(interact1).isNotEqualTo(interact2);
        interact1.setId(null);
        assertThat(interact1).isNotEqualTo(interact2);
    }
}
