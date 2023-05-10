package com.spring.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.zxing.WriterException;
import com.spring.dao.loginDao;
import com.spring.secuity.config.AESEncryptionDecryption;
import com.spring.secuity.config.QrCodeGeneration;

@Controller
public class LoginController {

	private static boolean validUser = false;

	@Autowired
	private LoginBean lbean;

	@Autowired
	loginDao loginDao;

	private int logAttempt = 0;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("welcomePage");
		return model;
	}

	@RequestMapping(value = { "/homePage" }, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("homePage");
		return model;
	}

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials provided.");
		}

		if (logout != null) {
			model.addObject("message", "Logged out from  successfully.");
		}

		model.setViewName("loginPage");
		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
		public String logout(HttpSession session) {
	        session.invalidate();
	        return "redirect:/home";
	      }
	    
	   
	@RequestMapping(value = { "/level1auth" }, method = RequestMethod.POST)
	public ModelAndView nextPage(@ModelAttribute LoginBean b, Model m) {
		BeanUtils.copyProperties(b, lbean);
		m.addAttribute(b);
		List listofImages = new ArrayList<>();
		lbean.setEmail(lbean.getEmail().replace(",", ""));
		LoginBean bean = loginDao.getUser(lbean.getEmail());

		if (bean.getLoginAttempt() >= 3) {
			return new ModelAndView("accountLock");
		} else {
			String rgbPassword = AESEncryptionDecryption.decrypt(bean.getPassword());

			if (b.getPassword().replace(",", "").equals(rgbPassword)) {
				validUser = true;
				byte[] imageData = AESEncryptionDecryption.decryptPdfFile(bean.getImageData());
				listofImages.add(org.apache.commons.codec.binary.Base64.encodeBase64String(imageData));
				lbean.setImageData(imageData);
				listofImages = loadImages(listofImages);
			} else {
				validUser = false;
				listofImages = loadImages(listofImages);
				m.addAttribute("userName", b.getUserName());
				m.addAttribute("email", b.getEmail());
				m.addAttribute("password", b.getPassword());
			}
		}
		m.addAttribute("userName", b.getUserName());
		m.addAttribute("email", b.getEmail());
		m.addAttribute("password", b.getPassword());
		m.addAttribute("imageList", listofImages);
		return new ModelAndView("loginStep2");
	}

	public List loadImages(List listofImages) {
		try {
			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + File.separator + "tmpFiles");
			if (!dir.exists())
				dir.mkdirs();
			// List of all files and directories
			String contents[] = dir.list();
			System.out.println("List of files and directories in the specified directory:");
			for (int i = 0; i < contents.length; i++) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
				File f = new File(dir + "/" + contents[i]);
				BufferedImage img = ImageIO.read(f);
				ImageIO.write(img, "jpg", baos);
				baos.flush();
				byte[] imageData = baos.toByteArray();
				baos.close();
				listofImages.add(org.apache.commons.codec.binary.Base64.encodeBase64String(imageData));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listofImages;
	}

	@RequestMapping(value = { "/validateImage" }, method = RequestMethod.POST)
	public ModelAndView validateImage(@ModelAttribute LoginBean b, Model m, RedirectAttributes redirectAttributes) {
		m.addAttribute(b);
		PreparedStatement ps = null;
		// Initially assigning null
		BufferedImage imgA = null;
		BufferedImage imgB = null;
		ResultSet rs = null;
		byte[] imageData = null;

		// Try block to check for exception
		try {
		
			// Reading file from local directory by
			// creating object of File class
			lbean.setEmail(lbean.getEmail().replace(",", ""));
			LoginBean bean = loginDao.getUser(lbean.getEmail());
			Path root = Paths.get(".").normalize().toAbsolutePath();
			File fileA = new File(root + "/choosenimage+.jpg");
			byte[] bytes = org.apache.commons.codec.binary.Base64.decodeBase64(b.getImageSource());
			InputStream is = new ByteArrayInputStream(bytes);
			BufferedImage newBi = ImageIO.read(is);
			ImageIO.write(newBi, "jpg", fileA);

			File fileB = new File(root + "/dbimage.jpg");
			ByteArrayInputStream bis = new ByteArrayInputStream(AESEncryptionDecryption.decryptPdfFile((bean.getImageData())));
			BufferedImage bImage2 = ImageIO.read(bis);
			ImageIO.write(bImage2, "jpg", fileB);

			// Reading files
			imgA = ImageIO.read(fileA);
			imgB = ImageIO.read(fileB);

			// Assigning dimensions to image
			int width1 = imgA.getWidth();
			int width2 = imgB.getWidth();
			int height1 = imgA.getHeight();
			int height2 = imgB.getHeight();

			// Checking whether the images are of same size or
			// not
			if ((width1 != width2) || (height1 != height2)) {

				// Display message straightaway
				System.out.println("Error: Images dimensions" + " mismatch");
				validUser = false;
				lbean.setLoginAttempt(bean.getLoginAttempt()+1);
			} else {

				// By now, images are of same size

				long difference = 0;

				// treating images likely 2D matrix

				// Outer loop for rows(height)
				for (int y = 0; y < height1; y++) {

					// Inner loop for columns(width)
					for (int x = 0; x < width1; x++) {

						int rgbA = imgA.getRGB(x, y);
						int rgbB = imgB.getRGB(x, y);
						int redA = (rgbA >> 16) & 0xff;
						int greenA = (rgbA >> 8) & 0xff;
						int blueA = (rgbA) & 0xff;
						int redB = (rgbB >> 16) & 0xff;
						int greenB = (rgbB >> 8) & 0xff;
						int blueB = (rgbB) & 0xff;

						difference += Math.abs(redA - redB);
						difference += Math.abs(greenA - greenB);
						difference += Math.abs(blueA - blueB);
					}
				}

				// Total number of red pixels = width * height
				// Total number of blue pixels = width * height
				// Total number of green pixels = width * height
				// So total number of pixels = width * height *
				// 3
				double total_pixels = width1 * height1 * 3;

				// Normalizing the value of different pixels
				// for accuracy

				// Note: Average pixels per color component
				double avg_different_pixels = difference / total_pixels;

				// There are 255 values of pixels in total
				double percentage = (avg_different_pixels / 255) * 100;

				// Lastly print the difference percentage
				System.out.println("Difference Percentage-->" + percentage);

				if (percentage == 0.0) {
					validUser = true;
					if (b.getTwoFactorCode() == null || b.getTwoFactorCode().equals("")) {
						emailWithEmbeddedImage(b, m);
						return new ModelAndView("bindingTwoFactorValidate");
					}
				} else {
					lbean.setLoginAttempt(lbean.getLoginAttempt()+1);
					validUser = false;
				}
			}
			if (loginDao.updatelockCount(lbean) == 0) {
				return new ModelAndView("error");
			}
		} catch (IOException e) {
			return new ModelAndView("error");
		}

		return new ModelAndView("error");
	}

	public String toBindingGoogleTwoFactorValidate(LoginBean b, Model model) {
		String randomSecretKey = QrCodeGeneration.generateSecretKey();
		String dir = "";
		// The parameters set in this step are the parameters displayed after App code
		// scanning
		String qrCodeString = QrCodeGeneration.spawnScanQRString(b.getEmail(), randomSecretKey, "pilipili2333");
		String qrCodeImageBase64 = null;
		try {
			dir = "D:\\Projects\\demo.png";
			qrCodeImageBase64 = QrCodeGeneration.createQRCode(qrCodeString, dir, 256, 256);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("randomSecretKey", randomSecretKey);
		model.addAttribute("qrCodeImageBase64", qrCodeImageBase64);

		return dir;
	}

	/**
	 * Perform Google two-step validation binding
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/bindingGoogleTwoFactorValidate" }, method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView bindingGoogleTwoFactorValidate(HttpSession session, @ModelAttribute LoginBean user, Model m) {
		String rightCode = QrCodeGeneration.getTOTPCode(user.getRandomKey());
		System.out.println("right code = " + rightCode);
		lbean.setEmail(lbean.getEmail().replace(",", ""));
		LoginBean bean = loginDao.getUser(lbean.getEmail());
		if (!rightCode.equals(user.getTwoFactorCode())) {
			lbean.setLoginAttempt(bean.getLoginAttempt()+1);
			loginDao.updatelockCount(lbean);
			return new ModelAndView("error");
		}
		if (bean != null) {
			m.addAttribute("user", bean.getUserName());
			m.addAttribute("email", bean.getEmail());
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
	        f.setTimeZone(TimeZone.getTimeZone("UTC"));
			m.addAttribute("loggedIn",f.format(new Date()));
		}
		
		return new ModelAndView("userProfile");
	}

	public void emailWithEmbeddedImage(LoginBean b, Model m) {

		try {

			String username = "swathisuresh2502@gmail.com";
			String password = "uvyafnumwgillcvu";

			// The JavaMail message object
			Message mesg;

			// SMTP server properties
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", 465);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", 465);

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			// initialize session object
			session.setDebug(false);

			// initialize message object
			mesg = new MimeMessage(session);

			// from Address
			mesg.setFrom(new InternetAddress(username));

			// Email Addresses
			InternetAddress toAdd = new InternetAddress(b.getEmail());

			mesg.addRecipient(Message.RecipientType.TO, toAdd);

			// email Subject
			mesg.setSubject("Login access code");

			// message body.
			Multipart mp = new MimeMultipart("related");

			String cid = "qr";

			String dir = toBindingGoogleTwoFactorValidate(b, m);

			MimeBodyPart pixPart = new MimeBodyPart();
			pixPart.attachFile(dir);
			pixPart.setContentID("<" + cid + ">");
			pixPart.setDisposition(MimeBodyPart.INLINE);

			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("<html>" + "Hello " + b.getUserName() + ", <br> " + "Please find your Login QR code <br> "
					+ "<div><img src=\"cid:" + cid + "\" /></div></html>" + "Thanks & Regards, <br> " + "Sender</html>",
					"US-ASCII", "html");

			// Attach text and image to message body
			mp.addBodyPart(textPart);
			mp.addBodyPart(pixPart);

			// Setting message content
			mesg.setContent(mp);

			// Send mail
			Transport.send(mesg);

		} catch (MessagingException e) {
			System.err.println(e);
			e.printStackTrace(System.err);
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView myprofile(Model m) {
		lbean.setEmail(lbean.getEmail().replace(",", ""));
		LoginBean bean = loginDao.getUser(lbean.getEmail());
		if (bean != null) {
			m.addAttribute("user", bean.getUserName());
			m.addAttribute("email", bean.getEmail());
		}
		
		return new ModelAndView("userProfile");
	}

}