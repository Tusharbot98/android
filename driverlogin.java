public class driverlogin extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener listener;
    private EditText mail , password;
    private Button log , res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverlogin);
        mail=(EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.pass);
        log=(Button)findViewById(R.id.login1);
        res=(Button)findViewById(R.id.registration);

        auth=FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent intent= new Intent(driverlogin.this,mapactivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mail.getText().toString();
                final String passwo = password.getText().toString();
                auth.createUserWithEmailAndPassword(email,passwo).addOnCompleteListener(driverlogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if (!task.isSuccessful()){

                         Toast.makeText(driverlogin.this,"sign up error",Toast.LENGTH_SHORT).show();
                     }else {
                         String user_id = auth.getCurrentUser().getUid();
                         DatabaseReference current_user_db= FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(user_id);
                         current_user_db.setValue(true);
                     }
                    }
                });
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mail.getText().toString();
                final String passwo = password.getText().toString();
                auth.signInWithEmailAndPassword(email,passwo).addOnCompleteListener(driverlogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){

                            Toast.makeText(driverlogin.this,"sign in error",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(listener);
    }
}
