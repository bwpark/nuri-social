package com.nuri.social.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nuri.social.web.rest.TestUtil;

public class AvatarCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvatarCategory.class);
        AvatarCategory avatarCategory1 = new AvatarCategory();
        avatarCategory1.setId("id1");
        AvatarCategory avatarCategory2 = new AvatarCategory();
        avatarCategory2.setId(avatarCategory1.getId());
        assertThat(avatarCategory1).isEqualTo(avatarCategory2);
        avatarCategory2.setId("id2");
        assertThat(avatarCategory1).isNotEqualTo(avatarCategory2);
        avatarCategory1.setId(null);
        assertThat(avatarCategory1).isNotEqualTo(avatarCategory2);
    }
}
