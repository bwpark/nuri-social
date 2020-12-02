package com.nuri.social.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.social.web.rest.TestUtil;

public class ChemistryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChemistryDTO.class);
        ChemistryDTO chemistryDTO1 = new ChemistryDTO();
        chemistryDTO1.setId("id1");
        ChemistryDTO chemistryDTO2 = new ChemistryDTO();
        assertThat(chemistryDTO1).isNotEqualTo(chemistryDTO2);
        chemistryDTO2.setId(chemistryDTO1.getId());
        assertThat(chemistryDTO1).isEqualTo(chemistryDTO2);
        chemistryDTO2.setId("id2");
        assertThat(chemistryDTO1).isNotEqualTo(chemistryDTO2);
        chemistryDTO1.setId(null);
        assertThat(chemistryDTO1).isNotEqualTo(chemistryDTO2);
    }
}
