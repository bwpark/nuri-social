package com.nuri.social.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.social.web.rest.TestUtil;

public class ChemistryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Chemistry.class);
        Chemistry chemistry1 = new Chemistry();
        chemistry1.setId("id1");
        Chemistry chemistry2 = new Chemistry();
        chemistry2.setId(chemistry1.getId());
        assertThat(chemistry1).isEqualTo(chemistry2);
        chemistry2.setId("id2");
        assertThat(chemistry1).isNotEqualTo(chemistry2);
        chemistry1.setId(null);
        assertThat(chemistry1).isNotEqualTo(chemistry2);
    }
}
