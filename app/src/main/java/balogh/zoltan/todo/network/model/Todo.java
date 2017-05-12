package balogh.zoltan.todo.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "")
public class Todo {

    @SerializedName("id")
    private Long id = null;

    @SerializedName("title")
    private String title = null;

    @SerializedName("content")
    private String content = null;

    @SerializedName("done")
    private Boolean done = false;


    /**
     **/
    @ApiModelProperty(value = "")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    /**
     **/
    @ApiModelProperty(value = "")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    /**
     **/
    @ApiModelProperty(value = "")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    /**
     **/
    @ApiModelProperty(value = "")
    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) &&
                Objects.equals(title, todo.title) &&
                Objects.equals(content, todo.content) &&
                Objects.equals(done, todo.done);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, done);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Todo {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    done: ").append(toIndentedString(done)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
