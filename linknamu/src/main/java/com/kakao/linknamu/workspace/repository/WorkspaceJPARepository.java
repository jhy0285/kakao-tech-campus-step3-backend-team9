package com.kakao.linknamu.workspace.repository;

import com.kakao.linknamu.workspace.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkspaceJPARepository extends JpaRepository<Workspace, Long> {

    @Query(value = "select w from Workspace w where w.user.userId=:userId and w.workspaceName =:workspaceName")
    Optional<Workspace> findByUserIdAndWorkspaceName(@Param("userId") Long userId, @Param("workspaceName") String workspaceName);
}
