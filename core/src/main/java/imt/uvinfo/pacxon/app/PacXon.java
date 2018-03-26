package imt.uvinfo.pacxon.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import imt.uvinfo.pacxon.modele.Heros;
import imt.uvinfo.pacxon.modele.Jeu;
import imt.uvinfo.pacxon.modele.Niveau;
import imt.uvinfo.pacxon.modele.TypeBloc;

public class PacXon implements ApplicationListener {
    private Viewport viewport;
    private SpriteBatch sprites;
    private ShapeRenderer shapes;
    private float elapsed;
    
    
    private TextureAtlas textures;

    // Taille fenêtre
    int largeurFenetre = 800;
    int hauteurFenetre = 600;
    // Accès au modele
    Jeu monJeu;
    // Garde en mémoire le sprite du héros, à afficher lorsque celui-ci ne bouge plus
    String dernierSpriteHeros;

    @Override
    public void create() {
        // le viewport gère la caméra quand la fenêtre change de taille
        viewport = new ScalingViewport(Scaling.fit, largeurFenetre, hauteurFenetre);

        // prise en compte de la transparence des couleurs
        Gdx.gl.glEnable(GL30.GL_BLEND);

        // rendu en batches
        sprites = new SpriteBatch();
        shapes = new ShapeRenderer();
        
        /* Init jeu */
        monJeu = new Jeu(3);
        
        // Chargement des textures
        textures = new TextureAtlas(Gdx.files.internal("Textures_64px.atlas"));
    	
        // Init sauvegarde du dernier état du héros (regarde par défaut vers la droite)
    	dernierSpriteHeros = "Textures_64px_heros_right";
    	
    }

    @Override
    public void render() {
    	
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

        // Temps passé total
        elapsed += Gdx.graphics.getDeltaTime();
        
        // Récupère le niveau
        Niveau niveauActuel = monJeu.getNiveauActuel();
        
        // Récupérer le héros
        Heros heros = monJeu.getNiveauActuel().getHeros();
        
        // Gestion des touches fléchées (direction du heros)
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        	heros.setFlagArrowRight();
        } else {
        	heros.unSetFlagArrowRight();
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
        	heros.setFlagArrowUp();
        } else {
        	heros.unSetFlagArrowUp();
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        	heros.setFlagArrowLeft();
        } else {
        	heros.unSetFlagArrowLeft();
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
        	heros.setFlagArrowDown();
        } else {
        	heros.unSetFlagArrowDown();
        }

        int i = 0;
        int j = 0;
        int totalLargeur = niveauActuel.getTerrain().getLargeur();
        int totalHauteur = niveauActuel.getTerrain().getHauteur();
    	int unitLargeur = largeurFenetre/totalLargeur;
        int unitHauteur = hauteurFenetre/totalHauteur;
        
        // Coloration du fond de la fenêtre
        Gdx.gl.glClearColor(0, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        shapes.begin(ShapeRenderer.ShapeType.Line);
        //shapes.setColor(0, 0.5f, 1, 0.3f);
        //shapes.rect(10, 10, 780, 580);
        // Pas de formes à créer
        shapes.end();

        sprites.setProjectionMatrix(viewport.getCamera().combined);
        sprites.begin();
        TypeBloc tmpBloc = null;
        // Pour chaque bloc du terrain
        for(i = 0; i < totalLargeur; i++) {
        	for(j = 0; j < totalHauteur; j++) {
        		// Récupération du type du bloc
            	tmpBloc = niveauActuel.getTerrain().getBloc(i, j);
            	// Affichage de l'image du bloc selon son type
        		if(tmpBloc == TypeBloc.Bordure) {
        			sprites.draw(textures.findRegion("Textures_64px_bordure"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		} else if(tmpBloc == TypeBloc.BlocNormal) {
        			sprites.draw(textures.findRegion("Textures_64px_blocnormal"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		} else if(tmpBloc == TypeBloc.Vide){
        			sprites.draw(textures.findRegion("Textures_64px_vide"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		} else if(tmpBloc == TypeBloc.Trace){
        			sprites.draw(textures.findRegion("Textures_64px_trace"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		} else if(tmpBloc == TypeBloc.TraceTouche){
        			sprites.draw(textures.findRegion("Textures_64px_tracetouche"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		} else {
        			// Bloc inconnu
        			sprites.draw(textures.findRegion("Textures_64px_none"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		}
        		
        	}
        	
        }
        
        // Affichage du héros selon sa direction
        if(heros.getDirectionY() > 0.0) {
        	sprites.draw(textures.findRegion("Textures_64px_heros_up"), (int)(heros.getPosX()*largeurFenetre), (int)(heros.getPosY()*hauteurFenetre), unitLargeur, unitHauteur);
        	dernierSpriteHeros = "Textures_64px_heros_up";
        } else if(heros.getDirectionY() < 0.0) {
        	sprites.draw(textures.findRegion("Textures_64px_heros_down"), (int)(heros.getPosX()*largeurFenetre), (int)(heros.getPosY()*hauteurFenetre), unitLargeur, unitHauteur);
        	dernierSpriteHeros = "Textures_64px_heros_down";
        } else if(heros.getDirectionX() > 0.0) {
        	sprites.draw(textures.findRegion("Textures_64px_heros_right"), (int)(heros.getPosX()*largeurFenetre), (int)(heros.getPosY()*hauteurFenetre), unitLargeur, unitHauteur);
        	dernierSpriteHeros = "Textures_64px_heros_right";
        } else if(heros.getDirectionX() < 0.0) {
        	sprites.draw(textures.findRegion("Textures_64px_heros_left"), (int)(heros.getPosX()*largeurFenetre), (int)(heros.getPosY()*hauteurFenetre), unitLargeur, unitHauteur);
        	dernierSpriteHeros = "Textures_64px_heros_left";
        } else {
        	// Le héros ne bouge pas, on continue d'afficher selon le sens précédent
        	sprites.draw(textures.findRegion(dernierSpriteHeros), (int)(heros.getPosX()*largeurFenetre), (int)(heros.getPosY()*hauteurFenetre), unitLargeur, unitHauteur);
        }
        
        // Affichage de tous les monstres selon leur direction
        for(i = 0; i < niveauActuel.getNbMonstres(); i++) {
        	if(niveauActuel.getMonstre(i).getDirectionX() < 0.0) {
            	sprites.draw(textures.findRegion("Textures_64px_monstre_left"), (int)(niveauActuel.getMonstre(i).getPosX()*largeurFenetre), (int)(niveauActuel.getMonstre(i).getPosY()*hauteurFenetre), niveauActuel.getMonstre(i).getLargeur()*unitLargeur, niveauActuel.getMonstre(i).getHauteur()*unitHauteur);
        	} else {
            	sprites.draw(textures.findRegion("Textures_64px_monstre_right"), (int)(niveauActuel.getMonstre(i).getPosX()*largeurFenetre), (int)(niveauActuel.getMonstre(i).getPosY()*hauteurFenetre), niveauActuel.getMonstre(i).getLargeur()*unitLargeur, niveauActuel.getMonstre(i).getHauteur()*unitHauteur);
        	}
        }

        sprites.end();
        
        /* On met à jour le niveau, la màj s'affichera à la frame suivante */
        /* La mise à jour ne se fait pas si le temps est trop grand, pour éviter des erreurs (ici 0.5 seconde : 2fps minimum) */
        /* TODO : Possibilité de créer un thread qui mettra à jour lui-même l'affichage, pour éviter des problèmes lorsqu'il n'y a pas de render */
        if(Gdx.graphics.getDeltaTime() <= 0.5) {
        	niveauActuel.update(Gdx.graphics.getDeltaTime());
        }
        
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
