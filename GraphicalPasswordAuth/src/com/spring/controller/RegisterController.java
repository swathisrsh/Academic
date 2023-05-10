package com.spring.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.dao.loginDao;
import com.spring.secuity.config.AESEncryptionDecryption;

import sun.misc.BASE64Encoder;

@Controller
@Scope("session")
public class RegisterController {

	private final int rows = 3; // You should decide the values for rows and cols variables
	private final int cols = 3;
	private final int chunks = rows * cols;
	public String colorcode = "";
	
	@Autowired
	RegistrationBean rbean;
	
	@Autowired
	loginDao loginDao;

	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		return model;
	}

	@RequestMapping(value = { "/nextStep" }, method = RequestMethod.POST)
	public ModelAndView nextPage(@ModelAttribute RegistrationBean b, Model m) {
		BeanUtils.copyProperties(b, rbean);
		m.addAttribute(b);
		m.addAttribute("userName", b.getUserName());
		m.addAttribute("email", b.getEmail());
		m.addAttribute("password", b.getPassword());
		return new ModelAndView("registerstep2");
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ModelAndView fileUpload(@RequestParam("thisfile") CommonsMultipartFile file,
			RedirectAttributes redirectAttrs, @ModelAttribute RegistrationBean b) {
		rbean.setImageSource(file.getOriginalFilename()); 
		// Getting bytes of file and
		// storing it in a byte array
		byte[] data = file.getBytes();
		if (data.length != 0) {
			try {
				/*	byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				
				 * File dir = new File(rootPath + File.separator + "tmpFiles"); if
				 * (!dir.exists()) dir.mkdirs();
				 * 
				 * // Create the file on server File serverFile = new File(dir.getAbsolutePath()
				 * + File.separator + file.getOriginalFilename()); BufferedOutputStream stream =
				 * new BufferedOutputStream(new FileOutputStream(serverFile));
				 * stream.write(bytes); stream.close();
				 */
				String base64imgString = imageToBase64(data);
				redirectAttrs.addAttribute("imgName", file.getOriginalFilename());
				redirectAttrs.addAttribute("imgContent", base64imgString);
				redirectAttrs.addAttribute("userName", b.getUserName());
				redirectAttrs.addAttribute("email", b.getEmail());
				redirectAttrs.addAttribute("password", AESEncryptionDecryption.encrypt(b.getPassword()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return new ModelAndView("redirect:/completeRegistration");
	}

	private static String imageToBase64(byte[] dataBytes) {
		// Encode byte array Base64
		BASE64Encoder encoder = new BASE64Encoder();
		if (dataBytes != null) {
			return encoder.encode(dataBytes);// Returns a Base64 encoded byte array string
		}
		return null;
	}

	@RequestMapping(value = { "/completeRegistration" }, method = RequestMethod.GET)
	public ModelAndView welcomePage(@RequestParam("imgName") String imgName, @RequestParam("imgContent") String base64imgString,@RequestParam("userName") String userName,@RequestParam("email") String email,@RequestParam("password") String password) {
		try {
			byte[] data = org.apache.commons.codec.binary.Base64.decodeBase64(base64imgString);
			Blob img_blob = null;
			img_blob = new SerialBlob(AESEncryptionDecryption.encryptPdfFile(data));
			rbean.setImageData(img_blob);
			rbean.setPassword(AESEncryptionDecryption.encrypt(rbean.getPassword()));
			loginDao.save(rbean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("registrationComplete", "userName",userName);
	}
}