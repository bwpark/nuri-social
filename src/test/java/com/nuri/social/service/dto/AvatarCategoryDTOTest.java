package com.nuri.social.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.social.web.rest.TestUtil;

public class AvatarCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvatarCategoryDTO.class);
        AvatarCategoryDTO avatarCategoryDTO1 = new AvatarCategoryDTO();
        avatarCategoryDTO1.setId("id1");
        AvatarCategoryDTO avatarCategoryDTO2 = new AvatarCategoryDTO();
        assertThat(avatarCategoryDTO1).isNotEqualTo(avatarCategoryDTO2);
        avatarCategoryDTO2.setId(avatarCategoryDTO1.getId());
        assertThat(avatarCategoryDTO1).isEqualTo(avatarCategoryDTO2);
        avatarCategoryDTO2.setId("id2");
        assertThat(avatarCategoryDTO1).isNotEqualTo(avatarCategoryDTO2);
        avatarCategoryDTO1.setId(null);
        assertThat(avatarCategoryDTO1).isNotEqualTo(avatarCategoryDTO2);
    }
}
