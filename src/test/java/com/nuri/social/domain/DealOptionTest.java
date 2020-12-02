package com.nuri.social.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.social.web.rest.TestUtil;

public class DealOptionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealOption.class);
        DealOption dealOption1 = new DealOption();
        dealOption1.setId("id1");
        DealOption dealOption2 = new DealOption();
        dealOption2.setId(dealOption1.getId());
        assertThat(dealOption1).isEqualTo(dealOption2);
        dealOption2.setId("id2");
        assertThat(dealOption1).isNotEqualTo(dealOption2);
        dealOption1.setId(null);
        assertThat(dealOption1).isNotEqualTo(dealOption2);
    }
}
