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
    
    
    private Animation<TextureRegion> idle, walk;
    private TextureAtlas textures;
    
    /* MON CODE */
    Jeu monJeu;
    private Texture vide;
    private Texture bordure;
    private Texture heros;
    private Texture monstre;

    @Override
    public void create() {
        // le viewport gère la caméra quand la fenêtre change de taille
        viewport = new ScalingViewport(Scaling.fit, 800, 600);

        // prise en compte de la transparence des couleurs
        Gdx.gl.glEnable(GL30.GL_BLEND);

        // rendu en batches
        sprites = new SpriteBatch();
        shapes = new ShapeRenderer();
        
        /* MON CODE */
        monJeu = new Jeu(3);
        
        
        textures = new TextureAtlas(Gdx.files.internal("blocsUnicolor.atlas"));
    	
    	

    	/*
        // le viewport gère la caméra quand la fenêtre change de taille
        viewport = new ScalingViewport(Scaling.fit, 800, 600);

        // prise en compte de la transparence des couleurs
        Gdx.gl.glEnable(GL30.GL_BLEND);

        // rendu en batches
        sprites = new SpriteBatch();
        shapes = new ShapeRenderer();

        textures = new TextureAtlas(Gdx.files.internal("alienGreen.atlas"));
        walk = new Animation<>(0.25f,
                               textures.findRegion("alienGreen_walk1"),
                               textures.findRegion("alienGreen_walk2"));
        idle = new Animation<>(2f, new Array<>(new TextureRegion[]{
                textures.findRegion("alienGreen"),
                textures.findRegion("alienGreen_stand")
        }), Animation.PlayMode.LOOP_RANDOM);
        
        
        
        */
    }

    @Override
    public void render() {
    	
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

        elapsed += Gdx.graphics.getDeltaTime();
        
        Niveau niveauActuel = monJeu.getNiveauActuel();
        
        Heros heros = monJeu.getNiveauActuel().getHeros();
        
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
    	int unitLargeur = 800/totalLargeur;
        int unitHauteur = 600/totalHauteur;
        
        Gdx.gl.glClearColor(0, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        shapes.begin(ShapeRenderer.ShapeType.Line);
        //shapes.setColor(0, 0.5f, 1, 0.3f);
        //shapes.rect(10, 10, 780, 580);
        shapes.end();

        sprites.setProjectionMatrix(viewport.getCamera().combined);
        sprites.begin();
        TypeBloc tmpBloc = null;
        for(i = 0; i < totalLargeur; i++) {
        	for(j = 0; j < totalHauteur; j++) {
            	tmpBloc = niveauActuel.getTerrain().getBloc(i, j);
        		if(tmpBloc == TypeBloc.Bordure) {
        			sprites.draw(textures.findRegion("blocsUnicolor_bordure"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		} else if(tmpBloc == TypeBloc.BlocNormal) {
        			sprites.draw(textures.findRegion("blocsUnicolor_blocnormal"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		} else if(tmpBloc == TypeBloc.Vide){
        			sprites.draw(textures.findRegion("blocsUnicolor_vide"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		} else if(tmpBloc == TypeBloc.Trace){
        			sprites.draw(textures.findRegion("blocsUnicolor_trace"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		} else if(tmpBloc == TypeBloc.TraceTouche){
        			sprites.draw(textures.findRegion("blocsUnicolor_tracetouche"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		} else {
        			sprites.draw(textures.findRegion("blocsUnicolor_none"), i*unitLargeur, j*unitHauteur, unitLargeur, unitHauteur);
        		}
        		
        	}
        	
        }
        
        if(heros.getDirectionY() > 0.0) {
        	sprites.draw(textures.findRegion("blocsUnicolor_heros_up"), (int)(heros.getPosX()*800), (int)(heros.getPosY()*600), unitLargeur, unitHauteur);
        } else if(heros.getDirectionY() < 0.0) {
        	sprites.draw(textures.findRegion("blocsUnicolor_heros_down"), (int)(heros.getPosX()*800), (int)(heros.getPosY()*600), unitLargeur, unitHauteur);
        } else if(heros.getDirectionX() < 0.0) {
        	sprites.draw(textures.findRegion("blocsUnicolor_heros_left"), (int)(heros.getPosX()*800), (int)(heros.getPosY()*600), unitLargeur, unitHauteur);
        } else {
        	sprites.draw(textures.findRegion("blocsUnicolor_heros_right"), (int)(heros.getPosX()*800), (int)(heros.getPosY()*600), unitLargeur, unitHauteur);
        }
        
        
        for(i = 0; i < niveauActuel.getNbPersonnages(); i++) {
        	if(niveauActuel.getPersonnage(i).getDirectionX() < 0.0) {
            	sprites.draw(textures.findRegion("blocsUnicolor_monstre_left"), (int)(niveauActuel.getPersonnage(i).getPosX()*800), (int)(niveauActuel.getPersonnage(i).getPosY()*600), niveauActuel.getPersonnage(i).getLargeur()*unitLargeur, niveauActuel.getPersonnage(i).getHauteur()*unitHauteur);
        	} else {
            	sprites.draw(textures.findRegion("blocsUnicolor_monstre_right"), (int)(niveauActuel.getPersonnage(i).getPosX()*800), (int)(niveauActuel.getPersonnage(i).getPosY()*600), niveauActuel.getPersonnage(i).getLargeur()*unitLargeur, niveauActuel.getPersonnage(i).getHauteur()*unitHauteur);
        	}
        }

        sprites.end();
        
        /* On pet � jour le niveau, la m�j s'affichera � la frame suivante */
        niveauActuel.update(Gdx.graphics.getDeltaTime());
        
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
