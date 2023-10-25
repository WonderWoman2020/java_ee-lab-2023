package skill.controller.api;

import skill.dto.GetSkillResponse;
import skill.dto.GetSkillsResponse;

import java.util.UUID;

public interface SkillController {

    GetSkillsResponse getSkills();

    GetSkillResponse getSkill(UUID uuid);
}
