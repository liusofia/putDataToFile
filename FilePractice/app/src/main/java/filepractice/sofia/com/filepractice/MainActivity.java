package filepractice.sofia.com.filepractice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText)findViewById(R.id.edit);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = edit.getText().toString();
        save(inputText);
    }

    //等待back出来，这个内容就存储下来了
    public void save(String inputText){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            //1:得到一个FileOutputStream对象
            //openFileOutput将数据存储到指定的文件中
            out = openFileOutput("data", Context.MODE_PRIVATE);
            //构建一个OutputStreamWriter对象
            //再构建一个BufferedWriter对象
            writer = new BufferedWriter(new OutputStreamWriter(out));
            //然后通过BufferedWriter将inputText文本内容写到文件中
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
