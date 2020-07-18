package com.gagful.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gagful.base.BaseEntity;
import com.gagful.dto.CommentDTO;
import com.gagful.dto.VoteDTO;
import com.gagful.dto.VoteUserDTO;
import com.gagful.util.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PostResponseDTO extends BaseEntity {

    @NotBlank
    @Size(min = 3, max = 15)
    private String title;

    @Lob
    private byte[] image;

    private String userUsername;

    private String categoryName;

    @Lob
    private byte[] categoryIcon;

    private boolean isSensitive = false;

    @JsonIgnore
    private List<CommentDTO> comments;

    @JsonIgnoreProperties({"id", "createdDate", "lastModifiedDate"})
    private List<VoteDTO> votes;

    @JsonIgnoreProperties({"id", "createdDate", "lastModifiedDate"})
    private List<VoteUserDTO> voteUsers;

    /*public byte[] getImage() {
        if (image != null && image.length > 0)
            return FileUtil.decompressBytes(image);
        else
            return this.image;
    }

    public byte[] getCategoryIcon() {
        if (categoryIcon != null && categoryIcon.length > 0)
            return FileUtil.decompressBytes(categoryIcon);
        else
            return this.categoryIcon;
    }*/
}