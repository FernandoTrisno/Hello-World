package com.example.helloworld;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

package com.example.helloworld;


    public class Mahasiswa {

        private String nim;
        private String nama;
        private String phone;

        public Mahasiswa(String nim, String nama, String phone) {
            this.nim = nim;
            this.nama = nama;
            this.phone = phone;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }


    }

        private void tambahMahasiswa() {

            Mahasiswa mhs = new Mahasiswa(noMhs.getText().toString(),
                    namaMhs.getText().toString(),
                    phoneMhs.getText().toString());

            firebaseFirestoreDb.collection("DaftarMhs").document().set(mhs)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(requireActivity(), "Mahasiswa berhasil didaftarkan",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(requireActivity(), "ERROR" + e.toString(),
                                    Toast.LENGTH_SHORT).show();
                            Log.d("TAG", e.toString());
                        }
                    });

            firebaseFirestoreDb = FirebaseFirestore.getInstance();

            buttonSimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //sanity check
                    if (!noMhs.getText().toString().isEmpty() && !namaMhs.getText().toString().isEmpty()) {
                        tambahMahasiswa();
                    } else {
                        Toast.makeText(requireActivity(), "No dan Nama Mhs tidak boleh kosong",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }

        private void getDataMahasiswa() {
            DocumentReference docRef = firebaseFirestoreDb.collection("DaftarMhs").document("mhs1");
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Mahasiswa mhs = document.toObject(Mahasiswa.class);
                            noMhs.setText(mhs.getNim());
                            namaMhs.setText(mhs.getNama());
                            phoneMhs.setText(mhs.getPhone());
                        } else {
                            Toast.makeText(requireActivity(), "Document tidak ditemukan",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(requireActivity(), "Document error : " + task.getException(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        private void deleteDataMahasiswa() {
            firebaseFirestoreDb.collection("DaftarMhs").document("mhs1")
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            noMhs.setText("");
                            namaMhs.setText("");
                            phoneMhs.setText("");
                            Toast.makeText(requireActivity(), "Mahasiswa berhasil dihapus",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(requireActivity(), "Error deleting document: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        

    }




