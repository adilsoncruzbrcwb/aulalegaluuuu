package aplicacao.novaaplicacao;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImagemActivity extends Activity {

    private ImageView imageDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem);
        imageDownload = (ImageView) findViewById(R.id.imageDownload);
    }

    public void carregarImagem(View v) throws MalformedURLException {
        DownloadImageAsync downloadImageAsync = new DownloadImageAsync();
        URL url = new URL("http//:www.google.com.br");
        downloadImageAsync.execute(url);
    }
    private class DownloadImageAsync extends AsyncTask<URL, Integer, Bitmap>{

        ProgressDialog progress;

        @Override
        protected void onPreExecute(){
            progress= ProgressDialog.show(ImagemActivity.this, "baixando imagem", "por favor, aguarde");
        }

        @Override
        protected Bitmap doInBackground(URL... params) {
            Bitmap myBitmap = null;
            try{
                myBitmap = Util.loadImage(params[0]);
            }catch (IOException e){
                Toast.makeText(ImagemActivity.this, "Imagem não carregada", Toast.LENGTH_SHORT).show();
            }
            return myBitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            if(bitmap != null){
                imageDownload.setImageBitmap(bitmap);
            }
            progress.dismiss();
        }
    }
}
