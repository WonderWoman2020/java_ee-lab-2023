package skill.controller.api;

import skill.dto.GetSkillResponse;
import skill.dto.GetSkillsResponse;
import skill.dto.PutSkillRequest;

import java.util.UUID;

public interface SkillController {

    GetSkillsResponse getSkills();

    GetSkillResponse getSkill(UUID uuid);

    void deleteSkill(UUID id);

    void putSkill(UUID id, PutSkillRequest request);
}
