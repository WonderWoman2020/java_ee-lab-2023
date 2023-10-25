package skill.controller.api;

import skill.dto.GetSkillResponse;

import java.util.UUID;

public interface SkillController {

    //GetSkillsResponse getSkills();

    GetSkillResponse getSkill(UUID uuid);
}
