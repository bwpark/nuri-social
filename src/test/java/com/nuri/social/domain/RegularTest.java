package com.nuri.social.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.social.web.rest.TestUtil;

public class RegularTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Regular.class);
        Regular regular1 = new Regular();
        regular1.setId("id1");
        Regular regular2 = new Regular();
        regular2.setId(regular1.getId());
        assertThat(regular1).isEqualTo(regular2);
        regular2.setId("id2");
        assertThat(regular1).isNotEqualTo(regular2);
        regular1.setId(null);
        assertThat(regular1).isNotEqualTo(regular2);
    }
}
