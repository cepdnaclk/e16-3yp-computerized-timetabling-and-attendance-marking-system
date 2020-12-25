package Group10.example.API.Model;

import java.util.List;

public class groupPayLoad {

    public List<String> idList;

    public String groupId;

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
