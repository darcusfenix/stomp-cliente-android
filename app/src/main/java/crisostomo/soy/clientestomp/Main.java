package crisostomo.soy.clientestomp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.java_websocket.WebSocket;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.client.StompClient;

public class Main extends AppCompatActivity {

    private static final String TAG = "MENSAJE RECIBIDO";
    private StompClient mStompClient;

    private TextView mTVMensajeRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTVMensajeRecibido = (TextView) findViewById(R.id.tvMensajeRecibido);

        mStompClient = Stomp.over(WebSocket.class, "ws://192.168.23.44:61614/stomp");
        mStompClient.connect();

        mStompClient.topic("/cemex/prospecto").subscribe(topicMessage -> {
            mTVMensajeRecibido.setText(topicMessage.getPayload());
            Log.d(TAG, topicMessage.getPayload());
        });

        mStompClient.send("/cemex/prospectos", "Holaaaa! CEMEX :)");

        //   mStompClient.disconnect();

    }
}
