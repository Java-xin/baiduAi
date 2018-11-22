package com.baidu.images.entity;

public class Imag {
    // 网络图片地址的URL
    private String url;

    // 本地图片地址
    private String localPath;

    // 检索时原样带回,最长256B。样例{"name":"周杰伦", "id":"666"}
    // 请注意，检索接口不返回原图，仅反馈当前填写的brief信息，所以调用该入库接口时，
    // brief信息请尽量填写可关联至本地图库的图片id或者图片url、图片名称等信息
    private String brief;

    // 1 - 65535范围内的整数
    // tag间以逗号分隔，最多2个tag。样例："100,11" ；检索时可圈定分类维度进行检索
    private String tags;

    // 检索时tag之间的逻辑， 0：逻辑and，1：逻辑or
    private String tag_logic;

    // 1 - 1000范围内的整数
    // 0。未指定分页时，默认返回前300个结果；接口返回数量最大限制1000条，
    // 如：起始位置为900，截取条数500条，接口也只返回第900 - 1000条的结果，共计100条
    private String pn;

    // 1 - 1000范围内的整数 分页功能，截取条数，例：250
    private String rn;

    //图片签名（和image、url三选一），支持批量删除，批量删除时请勿传image、url，最多支持1000个cont_sign列表，
    // 样例："932301884,1068006219;316336521,553141152;2491030726,1352091083"
    private String cont_sign;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTag_logic() {
        return tag_logic;
    }

    public void setTag_logic(String tag_logic) {
        this.tag_logic = tag_logic;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getCont_sign() {
        return cont_sign;
    }

    public void setCont_sign(String cont_sign) {
        this.cont_sign = cont_sign;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    @Override
    public String toString() {
        return "Imag{" +
                "url='" + url + '\'' +
                ", localPath='" + localPath + '\'' +
                ", brief='" + brief + '\'' +
                ", tags='" + tags + '\'' +
                ", tag_logic='" + tag_logic + '\'' +
                ", pn='" + pn + '\'' +
                ", rn='" + rn + '\'' +
                ", cont_sign='" + cont_sign + '\'' +
                '}';
    }
}
