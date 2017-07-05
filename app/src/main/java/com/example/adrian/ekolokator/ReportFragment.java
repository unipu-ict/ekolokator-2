package com.example.adrian.ekolokator;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import static android.app.Activity.RESULT_OK;
import static java.lang.System.out;


public class ReportFragment extends Fragment {

    public ReportData reportData = new ReportData();
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_FROM_GALLERY = 2;
    String photoPath;
    ImageView imageView;


    private OnFragmentInteractionListener mListener;

    public ReportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        reportData.Location = getActivity().getIntent().getExtras().getParcelable("Latlng");

        // drop down menu
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        for (GarbageType GType:GarbageType.values())
        {
            list.add(GType.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);
        spinner.setSelection(reportData.GType.ordinal());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for (GarbageType type : GarbageType.values()) {
                    if (type.ordinal() == position) {
                        reportData.GType = type;
                        return;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        EditText ime = (EditText) view.findViewById(R.id.Ime);
        ime.setText(reportData.FirstName);
        ime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reportData.FirstName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText prezime = (EditText) view.findViewById(R.id.Prezime);
        prezime.setText(reportData.LastName);
        prezime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reportData.LastName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText Email = (EditText) view.findViewById(R.id.EMail);
        Email.setText(reportData.Mail);
        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reportData.Mail = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText Telefon = (EditText) view.findViewById((R.id.Telefon));
        Telefon.setText(reportData.PhoneNumber);
        Telefon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reportData.PhoneNumber = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText Komentar = (EditText)view.findViewById(R.id.Komentar);
        Komentar.setText(reportData.Comments);
        Komentar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reportData.Comments = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText Adresa = (EditText)view.findViewById(R.id.AdresaText);
        Adresa.setText(reportData.LocationName);
        Adresa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reportData.LocationName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // slika

        imageView = (ImageView) view.findViewById(R.id.ReportImage);
        if (reportData.Image != null) {
            imageView.setImageURI(reportData.Image);
        }
        else
        {
            imageView.setImageResource(getResources().getIdentifier("no_picture", "drawable", getActivity().getPackageName()));
        }

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                reportData.Image = null;
                imageView.setImageResource(getResources().getIdentifier("no_picture", "drawable", getActivity().getPackageName()));
                Toast.makeText(getActivity(), "Image removed", Toast.LENGTH_SHORT);

                return false;
            }
        });

        Button slikaj = (Button) view.findViewById(R.id.Slikajte);
        slikaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        Button odaberite = (Button) view.findViewById(R.id.DodajtePostojecu);
        odaberite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_FROM_GALLERY);
            }
        });

        // Send button

        Button posalji = (Button) view.findViewById(R.id.Posalji);
        posalji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailText = getMailText();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[] {"toni.bileta@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Prijava " + reportData.GType.toString());
                i.putExtra(Intent.EXTRA_TEXT, mailText);

                Uri uri = reportData.Image;
                i.putExtra(Intent.EXTRA_STREAM, uri);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getView().getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    String getMailText()
    {
        String text = "Poštovani,\n\n";
        if ((reportData.FirstName != null && reportData.FirstName != "") || (reportData.LastName != null && reportData.LastName != ""))
        {
            text += "Zovem se " + reportData.FirstName + " " + reportData.LastName + ". ";
        }

        text += "Ovim putem podnosim prijavu " + reportData.GType.toString() + " ";

        text += "na lokaciji ";

        if (reportData.LocationName != null && reportData.LocationName != "")
        {
            text += reportData.LocationName + " (" + reportData.Location + ").";
        }
        else
        {
            text += reportData.Location + ".";
        }

        text += "\n";

        if (reportData.Comments != null && reportData.Comments != "")
        {
            text += "\n  Dodatni komentari:\n" + reportData.Comments;
        }

        if (reportData.Image != null)
        {
            text += "\nViše detalja u privitku.";
        }

        text += "\n\n";

        if (reportData.Mail != null && reportData.Mail != "")
        {
            text += "Moj E_Mail: " + reportData.Mail + "\n";
        }

        if (reportData.PhoneNumber != null && reportData.PhoneNumber != "")
        {
            text += "Moj broj telefona je: " + reportData.PhoneNumber;
        }

        text += "\n\nOva poruka je poslana preko mobilne aplikacije EkoLokator.";

        return text;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Environment.DIRECTORY_PICTURES);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File image = File.createTempFile(imageFileName, ".jpg", storageDir);
                FileOutputStream fos = new FileOutputStream(image);
                photo.compress(Bitmap.CompressFormat.JPEG, 90, fos);
                fos.flush();
                fos.close();
                reportData.Image = Uri.fromFile(image);
            } catch (IOException e) {
                Toast.makeText(getView().getContext(), "Could not save image", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        else if (requestCode == REQUEST_IMAGE_FROM_GALLERY && resultCode == RESULT_OK)
        {
            Uri targetUri = data.getData();
            if (targetUri != null)
            {
                reportData.Image = targetUri;
                imageView.setImageURI(reportData.Image);




                // declare a stream to read the image data from the SD Card.
                InputStream inputStream;

                // e are getting an input stream, based on the URI of the image.
                try {
                    inputStream = getActivity().getContentResolver().openInputStream(targetUri);
                    Bitmap tempPhoto = BitmapFactory.decodeStream(inputStream);
                        tempPhoto = Bitmap.createScaledBitmap(tempPhoto, tempPhoto.getWidth() / 12, tempPhoto.getHeight() / 12, true);
                    imageView.setImageBitmap(tempPhoto);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Unable to open image", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                imageView.setImageResource(getResources().getIdentifier("no_picture", "drawable", getActivity().getPackageName()));
                Toast.makeText(getActivity(), "Invalid image path!", Toast.LENGTH_LONG).show();
            }

        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
