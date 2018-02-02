public class MainActivity extends AppCompatActivity {

    private Button butd , butc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butd =(Button)findViewById(R.id.driver);
        butc=(Button)findViewById(R.id.customer);

        butd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,driverlogin.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        butc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,customerlogin.class);
                startActivity(intent);
                finish();
                return;

            }
        });

    }
}
