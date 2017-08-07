package com.project.mb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.project.utils.MyFile;

@ManagedBean
@ViewScoped
public class FormView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StreamedContent streamedContent;
	private List<MyFile> files = new ArrayList<MyFile>();
	private String selectedFileName;

	@PostConstruct
	public void init() {
		// System.out.println("***/*/*/*/*/" + System.getProperty("user.dir"));

		MyFile myFile1 = new MyFile(1, "pdf/1.pdf");
		MyFile myFile2 = new MyFile(2, "pdf/1.pdf");
		files.add(myFile1);
		files.add(myFile2);

	}

	public List<MyFile> getFiles() {
		return files;
	}

	public String getSelectedFileName() {
		return selectedFileName;
	}

	public void setSelectedFileName(String selectedFileName) {
		this.selectedFileName = selectedFileName;
	}

	public StreamedContent getStreamedContent() {
		return streamedContent;
	}

	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}

	public void onRowSelect(SelectEvent event) {
		String fileName = ((MyFile) event.getObject()).getName();

		// we use this for refill the stream
		selectedFileName = fileName;

		createStream(fileName);

	}

	private StreamedContent createStream(String fileName) {
		streamedContent = new DefaultStreamedContent(getData(fileName),
				"application/pdf", "downloaded_" + fileName);
		return streamedContent;
	}

	private InputStream getData(String fileName) {
		// pdf files under src\main\resources
		// File file = new File(System.getProperty("user.dir")
		// + "\\src\\main\\resources\\" + fileName);
		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return is;

	}

	// refill the stream
	public void refreshStream() {
		createStream(selectedFileName);
	}

	public String generateRandomIdForNotCaching() {
		return java.util.UUID.randomUUID().toString();
	}
}
