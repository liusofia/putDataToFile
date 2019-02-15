package filepractice.sofia.com.filepractice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText)findViewById(R.id.edit);

        //以下是从文件中读取数据

        String inputText = load();
        if(!TextUtils.isEmpty(inputText)){
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this,"Restoring succeed ",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = edit.getText().toString();
        save(inputText);
    }

    public String load(){
        FileInputStream in = null;
        BufferedReader read = null;
        StringBuilder content = new StringBuilder();
        try{
            //1:得到一个FileInputstream对象
            //它会在/data/data/<package name>/file路径下找这个文件
            in = openFileInput("data");
            read = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = read.readLine()) != null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(read != null){
                try{
                    read.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
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
