package com.nuri.social.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ChemistryMapperTest {

    private ChemistryMapper chemistryMapper;

    @BeforeEach
    public void setUp() {
        chemistryMapper = new ChemistryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(chemistryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(chemistryMapper.fromId(null)).isNull();
    }
}
