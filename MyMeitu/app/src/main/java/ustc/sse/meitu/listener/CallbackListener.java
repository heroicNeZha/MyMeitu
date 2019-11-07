package ustc.sse.meitu.listener;

public interface CallbackListener {

    void onSuccess(String response);

    void onError(String exception);
}
