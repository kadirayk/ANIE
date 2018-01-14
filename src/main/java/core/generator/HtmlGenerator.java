package core.generator;

import core.model.Interview;
import util.FileUtil;

public class HtmlGenerator {

	public String generatePage(Interview interview) {
		StringBuilder html = new StringBuilder();

		if (interview != null) {
			html.append("<html>\n");
			html.append("<body>\n");
			html.append(interview.toHTML());
			html.append("</body\n>");
			html.append("</html>");
		}

		FileUtil.writeToFile("data/" + interview.getContext() + ".html", html.toString());

		return html.toString();
	}

}
