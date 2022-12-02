package com.hongmai.clonfer.model.template;

/**
 * @author JiaweiWang
 * @date 2021/9/8
 * @description
 */
public class VerifyEmailTemplate {
    public static String generateVerifyEmailHtml(String username, String code) {
        String html =
                "<html lang=\"en\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "    <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <meta name=\"format-detection\" content=\"telephone=no, date=no, address=no, email=no\">\n" +
                "    <!--[if mso]>\n" +
                "    <xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml>\n" +
                "    <style>\n" +
                "      td,th,div,p,a,h1,h2,h3,h4,h5,h6 {font-family: \"Segoe UI\", sans-serif; mso-line-height-rule: exactly;}\n" +
                "    </style>\n" +
                "  <![endif]-->\n" +
                "    <title>Verify Email Address</title>\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Montserrat:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,200;1,300;1,400;1,500;1,600;1,700\" rel=\"stylesheet\" media=\"screen\">\n" +
                "    <style>\n" +
                "      .hover-underline:hover {\n" +
                "        text-decoration: underline !important;\n" +
                "      }\n" +
                "\n" +
                "      @keyframes spin {\n" +
                "        to {\n" +
                "          transform: rotate(360deg);\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      @keyframes ping {\n" +
                "\n" +
                "        75%,\n" +
                "        100% {\n" +
                "          transform: scale(2);\n" +
                "          opacity: 0;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      @keyframes pulse {\n" +
                "        50% {\n" +
                "          opacity: .5;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      @keyframes bounce {\n" +
                "\n" +
                "        0%,\n" +
                "        100% {\n" +
                "          transform: translateY(-25%);\n" +
                "          animation-timing-function: cubic-bezier(0.8, 0, 1, 1);\n" +
                "        }\n" +
                "\n" +
                "        50% {\n" +
                "          transform: none;\n" +
                "          animation-timing-function: cubic-bezier(0, 0, 0.2, 1);\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 600px) {\n" +
                "        .sm-leading-32 {\n" +
                "          line-height: 32px !important;\n" +
                "        }\n" +
                "\n" +
                "        .sm-px-24 {\n" +
                "          padding-left: 24px !important;\n" +
                "          padding-right: 24px !important;\n" +
                "        }\n" +
                "\n" +
                "        .sm-py-32 {\n" +
                "          padding-top: 32px !important;\n" +
                "          padding-bottom: 32px !important;\n" +
                "        }\n" +
                "\n" +
                "        .sm-w-full {\n" +
                "          width: 100% !important;\n" +
                "        }\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "\n" +
                "  <body style=\"margin: 0; padding: 0; width: 100%; word-break: break-word; -webkit-font-smoothing: antialiased; --bg-opacity: 1; background-color: #eceff1; background-color: rgba(236, 239, 241, var(--bg-opacity));\">\n" +
                "    <div style=\"display: none;\">请验证您的邮箱地址</div>\n" +
                "    <div role=\"article\" aria-roledescription=\"email\" aria-label=\"Verify Email Address\" lang=\"en\">\n" +
                "      <table style=\"font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif; width: 100%;\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "        <tbody><tr>\n" +
                "          <td align=\"center\" style=\"--bg-opacity: 1; background-color: #eceff1; background-color: rgba(236, 239, 241, var(--bg-opacity)); font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif;\" bgcolor=\"rgba(236, 239, 241, var(--bg-opacity))\">\n" +
                "            <table class=\"sm-w-full\" style=\"font-family: 'Montserrat',Arial,sans-serif; width: 600px;\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "              <tbody><tr>\n" +
                "                <td class=\"sm-py-32 sm-px-24\" style=\"font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif; padding: 48px; text-align: center;\" align=\"center\">\n" +
                "                  <a href=\"\">\n" +
                "                    <image xlink:href=\"\" width=\"155\" alt=\"云展会\" style=\"border: 0; max-width: 100%; line-height: 100%; vertical-align: middle;\">\n" +
                "                  </a>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td align=\"center\" class=\"sm-px-24\" style=\"font-family: 'Montserrat',Arial,sans-serif;\">\n" +
                "                  <table style=\"font-family: 'Montserrat',Arial,sans-serif; width: 100%;\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td class=\"sm-px-24\" style=\"--bg-opacity: 1; background-color: #ffffff; background-color: rgba(255, 255, 255, var(--bg-opacity)); border-radius: 4px; font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif; font-size: 14px; line-height: 24px; padding: 48px; text-align: left; --text-opacity: 1; color: #626262; color: rgba(98, 98, 98, var(--text-opacity));\" bgcolor=\"rgba(255, 255, 255, var(--bg-opacity))\" align=\"left\">\n" +
                "                        <p style=\"font-weight: 600; font-size: 18px; margin-bottom: 0;\">你好</p>\n" +
                "                        <p style=\"font-weight: 700; font-size: 20px; margin-top: 0; --text-opacity: 1; color: #ff5850; color: rgba(255, 88, 80, var(--text-opacity));\">" + username + "</p>\n" +
                "                        <p class=\"sm-leading-32\" style=\"font-weight: 600; font-size: 20px; margin: 0 0 16px; --text-opacity: 1; color: #263238; color: rgba(38, 50, 56, var(--text-opacity));\">\n" +
                "                          感谢注册云展会！\n" +
                "                        </p>\n" +
                "                        <p style=\"margin: 0 0 24px;\">\n" +
                "                          请在注册页面中输入下方验证码以完成注册流程，该邮件十分钟内有效。\n" +
                "                        </p>\n" +
                "                        <p style=\"margin: 0 0 24px;\">\n" +
                "                          如果进行注册的并非您本人，请忽略这封邮件或联系我们。\n" +
                "                        </p>\n" +
                "                        <table style=\"font-family: 'Montserrat',Arial,sans-serif;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                          <tbody><tr>\n" +
                "                            <td style=\"mso-padding-alt: 16px 24px; --bg-opacity: 1; background-color: #7367f0; background-color: rgba(115, 103, 240, var(--bg-opacity)); border-radius: 4px; font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif;\" bgcolor=\"rgba(115, 103, 240, var(--bg-opacity))\">\n" +
                "                              <a style=\"display: block; font-weight: 600; font-size: 14px; line-height: 100%; padding: 16px 24px; --text-opacity: 1; color: #ffffff; color: rgba(255, 255, 255, var(--text-opacity)); text-decoration: none;\">" + code + "</a>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody></table>\n" +
                "                        <table style=\"font-family: 'Montserrat',Arial,sans-serif; width: 100%;\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                          <tbody><tr>\n" +
                "                            <td style=\"font-family: 'Montserrat',Arial,sans-serif; padding-top: 32px; padding-bottom: 32px;\">\n" +
                "                              <div style=\"--bg-opacity: 1; background-color: #eceff1; background-color: rgba(236, 239, 241, var(--bg-opacity)); height: 1px; line-height: 1px;\">&zwnj;</div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody></table>\n" +
                "                        <p style=\"margin: 0 0 16px;\">此致, <br>Clonf云展会</p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td style=\"font-family: 'Montserrat',Arial,sans-serif; height: 20px;\" height=\"20\"></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td style=\"font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif; font-size: 12px; padding-left: 48px; padding-right: 48px; --text-opacity: 1; color: #eceff1; color: rgba(236, 239, 241, var(--text-opacity));\">\n" +
                "                        <p style=\"--text-opacity: 1; color: #263238; color: rgba(38, 50, 56, var(--text-opacity));\">\n" +
                "                          完成注册的同时我们默认您已阅读并认可\n" +
                "                          <a href=\"\" class=\"hover-underline\" style=\"--text-opacity: 1; color: #7367f0; color: rgba(115, 103, 240, var(--text-opacity)); text-decoration: none;\">用户协议</a> 和\n" +
                "                          <a href=\"\" class=\"hover-underline\" style=\"--text-opacity: 1; color: #7367f0; color: rgba(115, 103, 240, var(--text-opacity)); text-decoration: none;\">隐私协议</a>.\n" +
                "                        </p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td style=\"font-family: 'Montserrat',Arial,sans-serif; height: 16px;\" height=\"16\"></td>\n" +
                "                    </tr>\n" +
                "                  </tbody></table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody></table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody></table>\n" +
                "    </div>\n" +
                "  \n" +
                "\n" +
                "</body></html>";

        return html;
    }
}
