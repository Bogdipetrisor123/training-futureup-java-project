package ro.zynk.futureup.controllers.responses;

public class WalletTotalValueResponse extends BaseResponse {
    private Float value;

    public WalletTotalValueResponse(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
