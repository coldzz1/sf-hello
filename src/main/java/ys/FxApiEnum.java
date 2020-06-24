package ys;

public enum FxApiEnum implements ApiEnum{


    FXOO1("FX001","风险传导列表"),
    FX002("FX002","风险评分")
    ;

    private String name ;
    private String api;
    FxApiEnum(String api,String name){
        this.api=api;
        this.name=name;

    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getApi() {
        return null;
    }
}
