package com.example.conor.softwareapp.mainActivties;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.players.addInformation;
import com.example.conor.softwareapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import java.util.HashMap;
import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private User user;
    private Button addInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolProf);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        addInfo = (Button) findViewById(R.id.addInfo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        addInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent journalIntent = new Intent(profile.this, addInformation.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                profile.this.startActivity(journalIntent);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.super.onBackPressed();
            }
        });

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    user = snapshot.getValue(User.class);
                    if (firebaseUser.getUid().equals(user.getId())) {
                        ((TextView) findViewById(R.id.emailP)).setText(firebaseUser.getEmail());
                        ((TextView) findViewById(R.id.userP)).setText(user.getUserName());
                        ((TextView) findViewById(R.id.edc)).setText(user.getEducation());
                        ((TextView) findViewById(R.id.about)).setText(user.getAbout());
                        ((TextView) findViewById(R.id.address)).setText(user.getAddress());
                       CircleImageView profileImg = ((CircleImageView) findViewById(R.id.profileImg));
                        String photoUrl = user.getImageUrl();
                        Glide.with(profile.this).load(photoUrl).into(profileImg);
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

//    private void openImage() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent,"Select Picture"), IMAGE_REQUEST);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK
//                && data != null && data.getData() != null )
//        {
//            imageUri = data.getData();
//                if (uploadTask != null && uploadTask.isInProgress()) {
//                    Toast.makeText(getApplicationContext(), "Upload In Progress", Toast.LENGTH_LONG).show();
//                } else {
//                    uploadImg();
//                }
//        }
//    }

//
//    private String getFileExtension(Uri uri) {
//        ContentResolver contentResolver = getApplicationContext().getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//    }
//
//    private void uploadImg() {
//
//        if (imageUri != null) {
//            final StorageReference fileRef = storageReference.child(System.currentTimeMillis()
//                    + "." + getFileExtension(imageUri));
//            uploadTask = fileRef.putFile(imageUri);
//            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                @Override
//                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                    if (!task.isSuccessful()) {
//                        throw task.getException();
//                    }
//                    return fileRef.getDownloadUrl();
//                }
//            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                @Override
//                public void onComplete(@NonNull Task<Uri> task) {
//                    if (task.isSuccessful()) {
//                        Uri downLoadUri = task.getResult();
//                        String mUri = downLoadUri.toString();
//                        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
//                        HashMap<String, Object> map = new HashMap<>();
//                        map.put("imageUrl ", mUri);
//                        reference.updateChildren(map);
//                        Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
//
//                    }
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            });
//        } else {
//            Toast.makeText(getApplicationContext(), "No Image Selected", Toast.LENGTH_LONG).show();
//        }
//
//    }
    
    private void status(String status) {
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", status);
        reference.updateChildren(map);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        status("offline");
    }
}