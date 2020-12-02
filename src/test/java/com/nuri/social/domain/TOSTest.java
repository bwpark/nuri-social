package com.nuri.social.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.social.web.rest.TestUtil;

public class TOSTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TOS.class);
        TOS tOS1 = new TOS();
        tOS1.setId("id1");
        TOS tOS2 = new TOS();
        tOS2.setId(tOS1.getId());
        assertThat(tOS1).isEqualTo(tOS2);
        tOS2.setId("id2");
        assertThat(tOS1).isNotEqualTo(tOS2);
        tOS1.setId(null);
        assertThat(tOS1).isNotEqualTo(tOS2);
    }
}
