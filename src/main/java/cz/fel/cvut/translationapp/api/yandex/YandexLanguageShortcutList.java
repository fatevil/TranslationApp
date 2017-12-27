package cz.fel.cvut.translationapp.api.yandex;

import cz.fel.cvut.translationapp.api.LanguageShortcutList;

public class YandexLanguageShortcutList implements LanguageShortcutList {

	
	@Override
	public boolean contains(String language) throws UnsupportedLanguageException {
		return get(language) == null;
	}

	@Override
	public String get(String language) throws UnsupportedLanguageException {
		switch (language) {
		case "en":
			return "en";
		case "de":
			return "en";
		case "cz":
			return "cs";
		case "it":
			return "it";
		default:
			throw new UnsupportedLanguageException("Language " + language + " is not supported!");
		}
	}

}
// tt=Tatar,de=German,hi=Hindi,lo=Lao,pt=Portuguese,lt=Lithuanian,hr=Croatian,
// lv=Latvian,ht=Haitian,hu=Hungarian,yi=Yiddish,hy=Armenian,uk=Ukrainian,
// mg=Malagasy,id=Indonesian,mi=Maori,ur=Urdu,mk=Macedonian,pap=Papiamento,
// ml=Malayalam,mn=Mongolian,af=Afrikaans,mr=Marathi,uz=Uzbek,ms=Malay,
// el=Greek,mt=Maltese,en=English,eo=Esperanto,is=Icelandic,it=Italian,
// am=Amharic,my=Burmese,es=Spanish,zh=Chinese,et=Estonian,eu=Basque,
// ar=Arabic,vi=Vietnamese,mhr=Mari,ja=Japanese,ne=Nepali,az=Azerbaijani,
// fa=Persian,ro=Romanian,nl=Dutch,ba=Bashkir,udm=Udmurt,ceb=Cebuano,no=Norwegian,
// be=Belarusian,emj=Emoji,fi=Finnish,ru=Russian,bg=Bulgarian,bn=Bengali,
// fr=French,jv=Javanese,bs=Bosnian,ka=Georgian,si=Sinhalese,sk=Slovak,
// sl=Slovenian,ga=Irish,gd=Scottish Gaelic, ca=Catalan, sq=Albanian,
// sr=Serbian,
// kk=Kazakh, km=Khmer, su=Sundanese, kn=Kannada, sv=Swedish, ko=Korean,
// mrj=Hill Mari,
// sw=Swahili, gl=Galician, ta=Tamil, gu=Gujarati, ky=Kyrgyz, cs=Czech,
// xh=Xhosa, pa=Punjabi,
// te=Telugu, tg=Tajik, th=Thai, la=Latin, cy=Welsh, lb=Luxembourgish,
// tl=Tagalog, pl=Polish,
// da=Danish, he=Hebrew, tr=Turkish