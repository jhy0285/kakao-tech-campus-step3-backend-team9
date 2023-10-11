package com.kakao.linknamu.notion.entity;

import com.kakao.linknamu._core.util.AuditingEntity;
import com.kakao.linknamu.category.entity.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="notion_page_tb", uniqueConstraints = {
                @UniqueConstraint(name = "uk_notion_account_page",
                        columnNames = {"notion_account_id", "page_id"})
        }
)
public class NotionPage extends AuditingEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notion_page_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notion_account_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private NotionAccount notionAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @Column(name = "page_id", nullable = false)
    private String pageId;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Builder
    public NotionPage(Long id, NotionAccount notionAccount, Category category, String pageId, Boolean isActive) {
        this.id = id;
        this.notionAccount = notionAccount;
        this.category = category;
        this.pageId = pageId;
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotionPage that = (NotionPage) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getPageId(), that.getPageId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPageId());
    }
}
