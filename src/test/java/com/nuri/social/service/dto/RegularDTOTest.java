package com.nuri.social.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.social.web.rest.TestUtil;

public class RegularDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegularDTO.class);
        RegularDTO regularDTO1 = new RegularDTO();
        regularDTO1.setId("id1");
        RegularDTO regularDTO2 = new RegularDTO();
        assertThat(regularDTO1).isNotEqualTo(regularDTO2);
        regularDTO2.setId(regularDTO1.getId());
        assertThat(regularDTO1).isEqualTo(regularDTO2);
        regularDTO2.setId("id2");
        assertThat(regularDTO1).isNotEqualTo(regularDTO2);
        regularDTO1.setId(null);
        assertThat(regularDTO1).isNotEqualTo(regularDTO2);
    }
}
