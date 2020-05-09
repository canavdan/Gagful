package com.gagful.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gagful.base.BaseEntity;
import com.gagful.dto.*;
import com.gagful.util.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PostRequestDTO extends BaseEntity {

    @NotBlank
    @Size(min = 3, max = 15)
    private String title;

    @Lob
    @NotEmpty
    private byte[] image;

    private UserDTO user;


    private CategoryDTO category;

    private boolean isSensitive = false;

    @JsonIgnore
    private List<CommentDTO> comments;

    @JsonIgnoreProperties({"id", "createdDate", "lastModifiedDate"})
    private List<VoteDTO> votes;

    @JsonIgnoreProperties({"id", "createdDate", "lastModifiedDate"})
    private List<VoteUserDTO> voteUsers;

    public void setImage(byte[] image) {
        if (image != null && image.length > 0)
            this.image = FileUtil.compressBytes(image);
    }

}
