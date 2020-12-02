package com.nuri.social.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.social.web.rest.TestUtil;

public class TOSDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TOSDTO.class);
        TOSDTO tOSDTO1 = new TOSDTO();
        tOSDTO1.setId("id1");
        TOSDTO tOSDTO2 = new TOSDTO();
        assertThat(tOSDTO1).isNotEqualTo(tOSDTO2);
        tOSDTO2.setId(tOSDTO1.getId());
        assertThat(tOSDTO1).isEqualTo(tOSDTO2);
        tOSDTO2.setId("id2");
        assertThat(tOSDTO1).isNotEqualTo(tOSDTO2);
        tOSDTO1.setId(null);
        assertThat(tOSDTO1).isNotEqualTo(tOSDTO2);
    }
}
