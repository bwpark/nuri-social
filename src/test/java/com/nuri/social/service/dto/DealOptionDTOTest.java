package com.nuri.social.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.social.web.rest.TestUtil;

public class DealOptionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealOptionDTO.class);
        DealOptionDTO dealOptionDTO1 = new DealOptionDTO();
        dealOptionDTO1.setId("id1");
        DealOptionDTO dealOptionDTO2 = new DealOptionDTO();
        assertThat(dealOptionDTO1).isNotEqualTo(dealOptionDTO2);
        dealOptionDTO2.setId(dealOptionDTO1.getId());
        assertThat(dealOptionDTO1).isEqualTo(dealOptionDTO2);
        dealOptionDTO2.setId("id2");
        assertThat(dealOptionDTO1).isNotEqualTo(dealOptionDTO2);
        dealOptionDTO1.setId(null);
        assertThat(dealOptionDTO1).isNotEqualTo(dealOptionDTO2);
    }
}
